(function() {
    "use strict";

    window.addEventListener("load", init);

    var exclamationCounter = 0;
    var total;

    function init() {
        let rollButton = id('roll');
        rollButton.addEventListener('click', rollDice);
        let submitButton1 = id('enter1');
        submitButton1.addEventListener('click', submitValue1);
        let submitButton2 = id('enter2');
        submitButton2.addEventListener('click', submitValue2);
        let submitButton3 = id('enter3');
        submitButton3.addEventListener('click', submitValue3);
        let submitButton4 = id('enter4');
        submitButton4.addEventListener('click', submitValue4);
        let submitButton5 = id('enter5');
        submitButton5.addEventListener('click', submitValue5);
    }

    function rollDice() {
        let status = id('status');
        if(exclamationCounter == 0) {
        const roll1 = Math.floor(Math.random()*6)+1;
        const roll2 = Math.floor(Math.random()*6)+1;
        total = roll1 + roll2;
        status.textContent = `${roll1} + ${roll2} = ${total}`;
        exclamationCounter++;
        } else {
            for(var i = 0; i < exclamationCounter; i++) {
                status.textContent = status.textContent + '!';
            }
        }
    }

    function submitValue1() {
        let status = id('status');
        let player = id('user1');
        if(exclamationCounter >= 1 && player.value == '') {
            player.value = total;
            exclamationCounter = 0;
            AITurn();
        } else {
            status.textContent = status.textContent + '!';
        };
    }

    function submitValue2() {
        let status = id('status');
        let player = id('user2');
        if(exclamationCounter >= 1 && player.value == '') {
            player.value = total;
            exclamationCounter = 0;
            AITurn();
        } else {
            status.textContent = status.textContent + '!';
        };
    }

    function submitValue3() {
        let status = id('status');
        let player = id('user3');
        if(exclamationCounter >= 1 && player.value == '') {
            player.value = total;
            exclamationCounter = 0;
            AITurn();
        } else {
            status.textContent = status.textContent + '!';
        };
    }

    function submitValue4() {
        let status = id('status');
        let player = id('user4');
        if(exclamationCounter >= 1 && player.value == '') {
            player.value = total;
            exclamationCounter = 0;
            AITurn();
        } else {
            status.textContent = status.textContent + '!';
        };
    }

    function submitValue5() {
        let status = id('status');
        let player = id('user5');
        if(exclamationCounter >= 1 && player.value == '') {
            player.value = total;
            exclamationCounter = 0;
            AITurn();
        } else {
            status.textContent = status.textContent + '!';
        };
    }

    function AITurn() {
        let status = id('status');
        let place1 = id('computer1');
        let place2 = id('computer2');
        let place3 = id('computer3');
        let place4 = id('computer4');
        let place5 = id('computer5');
        var theOneThatMakesYouNotRepeatNumbers = 0;
        if(place1.value == '' || place2.value == '' || place3.value == '' || place4.value == '' || place5.value == '') {
            while(theOneThatMakesYouNotRepeatNumbers == 0) {
        const AIPlace = Math.floor(Math.random()*5)+1;
        if(AIPlace == 1) {
            if(place1.value == '') {
            rollDice();
            exclamationCounter--;
            place1.value = total;
            theOneThatMakesYouNotRepeatNumbers++;
        }
    }
        if(AIPlace == 2) {
            if(place2.value == '') {
                rollDice();
                exclamationCounter--;
                place2.value = total;
                theOneThatMakesYouNotRepeatNumbers++;
        }
    }
        if(AIPlace == 3) {
            if(place3.value == '') {
                rollDice();
                exclamationCounter--;
                place3.value = total;
                theOneThatMakesYouNotRepeatNumbers++;
        }
    }
        if(AIPlace == 4) {
            if(place4.value == '') {
                rollDice();
                exclamationCounter--;
                place4.value = total;
                theOneThatMakesYouNotRepeatNumbers++;
        }
    }
        if(AIPlace == 5) {
            if(place5.value == '') {
                rollDice();
                exclamationCounter--;
                place5.value = total;
                theOneThatMakesYouNotRepeatNumbers++;
            }
        }
    }
    }
    status.textContent = 'Computer: '+ status.textContent
    if(!place1.value == '' && !place2.value == '' && !place3.value == '' && !place4.value == '' && !place5.value == '') {
        endGame();
    }
}
    function endGame() {
        var userPoints = 0;
        var computerPoints = 0;
        let status = id('status');
        let user1 = id('user1');
        let user2 = id('user2');
        let user3 = id('user3');
        let user4 = id('user4');
        let user5 = id('user5');
        let computer1 = id('computer1');
        let computer2 = id('computer2');
        let computer3 = id('computer3');
        let computer4 = id('computer4');
        let computer5 = id('computer5');
        if(user1.value > computer1.value) {
            userPoints+=1;
        } else if(user1.value < computer1.value) {
            computerPoints+=1;
        }
        if(user2.value > computer2.value) {
            userPoints+=2;
        } else if(user2.value < computer2.value) {
            computerPoints+=2;
        }
        if(user3.value > computer3.value) {
            userPoints+=3;
        } else if(user3.value < computer3.value) {
            computerPoints+=3;
        }
        if(user4.value > computer4.value) {
            userPoints+=4;
        } else if(user4.value < computer4.value) {
            computerPoints+=4;
        }
        if(user5.value > computer5.value) {
            userPoints+=5;
        } else if(user5.value < computer5.value) {
            computerPoints+=5;
        }
        if(userPoints>computerPoints) {
            status.textContent = `You win: ${userPoints}-${computerPoints}`;
        } else if(userPoints<computerPoints) {
            status.textContent = `You lose: ${userPoints}-${computerPoints}`;
        } else if (userPoints == computerPoints) {
            status.textContent = `You tie: ${userPoints}-${computerPoints}`;
        }
        exclamationCounter++;
    }
    /////////////////////////////////////////////////////////////////////
    // Helper functions
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