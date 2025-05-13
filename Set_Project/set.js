(function() {
    'use strict';

    window.addEventListener('load', init);
    let timerId = qs('option');
    let remainingSeconds = 0;
    const attributes = [["green", "purple", "red"], ["outline", "solid", "striped"], ["diamond", "oval", "squiggle"], [1, 2, 3]];

    function init() {
        let toggleViewButton = qsa('#start-btn, #back-btn');
        toggleViewButton.forEach(btn => btn.addEventListener('click', toggleView));
        let startButton = id('start-btn');
        startButton.addEventListener('click', newGame);
        let menuButton = id('back-btn');
        menuButton.addEventListener('click', endGame);
    }

    function toggleView() {
        let menuView = id('menu-view');
        let gameView = id('game-view')
        if(menuView.classList.contains('hidden')) {
            menuView.classList.remove('hidden');
            gameView.classList.add('hidden');
        } else if(gameView.classList.contains('hidden')) {
            gameView.classList.remove('hidden');
            menuView.classList.add('hidden');
        }
    }

    /**
    * Checks to see if the three selected cards make up a valid set. This is done by comparing each
    * of the type of attribute against the other two cards. If each four attributes for each card are
    * either all the same or all different, then the cards make a set. If not, they do not make a set
    * @param {DOMList} selected - List of all selected cards to check if a set.
    * @return {boolean} True if valid set false otherwise.
    */
    function isASet(selected) {
        let attributes = [];
        for (let i = 0; i < selected.length; i++) {
        attributes.push(selected[i].id.split("-"));
        }
        for (let i = 0; i < attributes[0].length; i++) {
        let allSame = attributes[0][i] === attributes[1][i] &&
                        attributes[1][i] === attributes[2][i];
        let allDiff = attributes[0][i] !== attributes[1][i] &&
                        attributes[1][i] !== attributes[2][i] &&
                    attributes[0][i] !== attributes[2][i];
        if (!(allDiff || allSame)) {
            return false;
        }
        }
        return true;
    }

    function newGame() {
        let difficultySelect = qs('input[name="diff"]:checked');
        let isEasy = false;
        if(difficultySelect.value == 'easy') {
            isEasy = true;
        }
        startTimer();  
        newBoard(isEasy);
        let refreshButton = id('refresh-btn');
        refreshButton.addEventListener('click', function() {if(remainingSeconds!=0) {newBoard(isEasy)}});
        let setCount = id('set-count');
        setCount.textContent = '0';
    }

    function newBoard(isEasy) {
        let subtractor = 0;
        let theBoard = id('board');
        theBoard.innerHTML = '';
        if(isEasy) {
            subtractor = 3;
        }
        for(let i = 0; i < 12-subtractor; i++) {
            theBoard.appendChild(generateUniqueCard(isEasy));
        }
    }

    function generateRandomAttributes(isEasy) {
        let attribute1 = attributes[0][Math.floor(Math.random()*3)];
        let attribute2 = attributes[1][Math.floor(Math.random()*3)];
        let attribute3 = attributes[2][Math.floor(Math.random()*3)];
        let attribute4 = attributes[3][Math.floor(Math.random()*3)];
        if(isEasy) {
            attribute2 = attributes[1][1];
        }
        let attributeArray = [attribute1, attribute2, attribute3, attribute4];
        return attributeArray;
    }

    function generateUniqueCard(isEasy) {
        let newCard = generateRandomAttributes(isEasy);
        let cardName = `${newCard[0]}-${newCard[1]}-${newCard[2]}-${newCard[3]}`;
        while(id(`${cardName}`) != null) {
            newCard = generateRandomAttributes(isEasy);
            cardName = `${newCard[0]}-${newCard[1]}-${newCard[2]}-${newCard[3]}`;
        }
        const makeDiv = document.createElement('div');
        makeDiv.id = `${cardName}`;
        makeDiv.classList.add('card');
        for(let i = 0; i < newCard[3]; i++) {
            let makeShapes = document.createElement('img');
            makeShapes.src = `img/${newCard[0]}-${newCard[1]}-${newCard[2]}.png`;
            makeShapes.alt = `${newCard[0]}-${newCard[1]}-${newCard[2]}-${newCard[3]}`;
            makeDiv.appendChild(makeShapes);
        }
        makeDiv.addEventListener('click', cardSelected);
        return makeDiv;
    }

    function startTimer() {
        timerId = qsa('option')[qs('select').selectedIndex].value;
        remainingSeconds = timerId;
        let displayTime = id('time');
        let minutes = parseInt(remainingSeconds/60);
        let seconds = parseInt(remainingSeconds%60);
        let minutesStr = '0' + `${minutes}`;
        let secondsStr = '00';
        if (seconds < 10) {
                    secondsStr = '0' + `${seconds}`;
                } else {
                    secondsStr = `${seconds}`;
                }
        displayTime.textContent = minutesStr + ':' + secondsStr;
        let theTimer = setInterval(function advanceTimer() {
            remainingSeconds--;
            minutes = parseInt(remainingSeconds/60);
            seconds = parseInt(remainingSeconds%60);
            minutesStr = '0' + `${minutes}`;
                if (seconds < 10) {
                    secondsStr = '0' + `${seconds}`;
                } else {
                    secondsStr = `${seconds}`;
                }
    
            displayTime.textContent = minutesStr + ':' + secondsStr;
            if(remainingSeconds < 0) {
                remainingSeconds = 0;
                displayTime.textContent = '00:00';
                clearInterval(theTimer);
            } 
        }, 1000);
    }

    function cardSelected() {
        if(remainingSeconds != 0) {
        let setCount = id('set-count');
        if(this.classList.contains('selected')) {
            this.classList.remove('selected');
        } else {
            this.classList.add('selected');
        }
        if(qsa('.selected').length == 3) {
            let groupOf3 = qsa('.selected');
            if(isASet(qsa('.selected'))) {
                setCount.textContent = parseInt(setCount.textContent)+1;
                groupOf3.forEach(card => card.textContent = 'SET!');
                let difficultySelect = qs('input[name="diff"]:checked');
                let isEasy = false;
                if(difficultySelect.value == 'easy') {
                    isEasy = true;
                }
                setTimeout(function newCards() {
                    groupOf3.forEach(thing => thing.replaceWith(generateUniqueCard(isEasy)));
                }, 1000);
            } else {
                remainingSeconds = remainingSeconds-15;
                    let placeHolder1 = document.createElement('p');
                    placeHolder1.textContent = 'Not a Set :(';
                    let placeHolder2 = document.createElement('p');
                    placeHolder2.textContent = 'Not a Set :(';
                    let placeHolder3 = document.createElement('p');
                    placeHolder3.textContent = 'Not a Set :(';
                groupOf3[0].appendChild(placeHolder1);
                groupOf3[1].appendChild(placeHolder2);
                groupOf3[2].appendChild(placeHolder3);
                groupOf3.forEach(img => img.classList.add('hide-imgs'));
            setTimeout(function removeSelected() {groupOf3.forEach(baller => baller.classList.remove('selected'));
                groupOf3.forEach(skibidi => skibidi.classList.remove('hide-imgs'));
                groupOf3.forEach(edge => edge.lastChild.remove());
            }, 1000);
            }
        }
    }
    }

    function endGame() {
        let theBoard = id('board');
        remainingSeconds = 0;
        theBoard.innerHTML = '';
    }

    /////////////////////////////////////////////////////////////////////
    // Helper functions
    /**
    * Helper function to return the response's result text if successful, otherwise
    * returns the rejected Promise result with an error status and corresponding text
    * @param {object} res - response to check for success/error

    * @return {object} - valid response if response was successful, otherwise rejected
    *                    Promise result
    */
    async function statusCheck(res) {
        if (!res.ok) {
            throw new Error(await res.text());
        }
        return res;
    }

    function id(id) {
        return document.getElementById(id);
    }

    function qs(selector) {
        return document.querySelector(selector);
    }

    function qsa(selector) {
        return document.querySelectorAll(selector);
    }
})();