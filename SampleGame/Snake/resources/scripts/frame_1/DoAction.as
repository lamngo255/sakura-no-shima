function startGame(pRec)
{
   x = int(gameWidth / 2);
   y = gameHeight - 2;
   map = new Array();
   var _loc1_ = 0;
   while(_loc1_ < gameWidth)
   {
      map[_loc1_] = new Array();
      _loc1_ = _loc1_ + 1;
   }
   turnQueue = new Array();
   game.createEmptyMovieClip("food",1);
   game.createEmptyMovieClip("s",2);
   scoreTextField.text = "Score: 0";
   foodCounter = 0;
   snakeBlockCounter = 0;
   currentDirection = 1;
   snakeEraseCounter = -1;
   score = 0;
   ticks = lastRec = 0;
   recPos = recFoodPos = 0;
   playRec = pRec;
   §§push(_loc1_);
   if(!playRec)
   {
      textMC.gotoAndStop("hide");
      highscores.enterHighscoreMC._visible = false;
      statusTextField.text = "";
      recTurn = "";
      recFrame = "";
      recFood = "";
      game.onEnterFrame = main;
   }
   else
   {
      if(loadedRecordingNumber != -1)
      {
         _loc1_ = getLoadedRecordingNumberHighscorePos(loadedRecordingNumber);
         statusTextField.text = "Viewing " + highscores[_loc1_].name.text + "\'s game (score " + highscores[_loc1_].score.text + ")";
      }
      else
      {
         statusTextField.text = "Viewing your game";
      }
      game.onEnterFrame = replayMain;
   }
   placeFood("new");
   gameRunning = true;
   _loc1_ = §§pop();
}
function main()
{
   §§push(_loc1_);
   §§push(_loc2_);
   §§push(_loc3_);
   if(playRec)
   {
      if(ticks == lastRec + parseInt(recFrame.charAt(recPos * 2) + recFrame.charAt(recPos * 2 + 1),36))
      {
         currentDirection = parseInt(recTurn.charAt(recPos));
         lastRec = ticks;
         recPos++;
      }
   }
   else if(turnQueue.length)
   {
      var _loc3_ = turnQueue.pop();
      if(_loc3_ % 2 != currentDirection % 2)
      {
         currentDirection = _loc3_;
         recTurn = recTurn + _loc3_;
         var _loc2_ = ticks - lastRec;
         if(_loc2_ < 36)
         {
            recFrame = recFrame + (" " + new Number(_loc2_).toString(36));
         }
         else
         {
            recFrame = recFrame + new Number(_loc2_).toString(36);
         }
         lastRec = ticks;
      }
   }
   x = x + xVelocity[currentDirection];
   y = y + yVelocity[currentDirection];
   if(map[x][y] != SNAKE_BLOCK && x > -1 && x < gameWidth && y > -1 && y < gameHeight)
   {
      game.s.attachMovie("snakeMC",snakeBlockCounter,snakeBlockCounter,{_x:x * blockSize,_y:y * blockSize});
      snakeBlockCounter++;
      if(map[x][y])
      {
         score = score + 10;
         scoreTextField.text = "Score: " + score;
         snakeEraseCounter = snakeEraseCounter - 5;
         placeFood(map[x][y]);
      }
      map[x][y] = SNAKE_BLOCK;
      var _loc1_ = game.s[snakeEraseCounter];
      if(_loc1_)
      {
         delete map[_loc1_._x / blockSize]._loc1_._y / blockSize;
         _loc1_.removeMovieClip();
      }
      snakeEraseCounter++;
      ticks++;
   }
   else if(playRec)
   {
      startGame(true);
   }
   else
   {
      gameOver();
   }
   _loc3_ = §§pop();
   _loc2_ = §§pop();
   _loc1_ = §§pop();
}
function replayMain()
{
   var _loc1_ = 0;
   while(_loc1_ < replaySpeed)
   {
      main();
      _loc1_ = _loc1_ + 1;
   }
   _loc1_;
}
function gameOver()
{
   textMC.gotoAndStop("gameOver");
   delete game.onEnterFrame;
   gameRunning = false;
   enterHighscore();
}
function placeFood(foodMC)
{
   var _loc3_ = foodMC;
   §§push(_loc1_);
   §§push(_loc2_);
   §§push(_loc3_);
   if(playRec)
   {
      var _loc1_ = parseInt(recFood.charAt(recFoodPos * 3) + recFood.charAt(recFoodPos * 3 + 1),36);
      var _loc2_ = parseInt(recFood.charAt(recFoodPos * 3 + 2),36);
      recFoodPos++;
   }
   else
   {
      do
      {
         _loc1_ = random(gameWidth);
         _loc2_ = random(gameHeight);
      }
      while(map[_loc1_][_loc2_]);
      
      if(_loc1_ < 36)
      {
         recFood = recFood + (" " + new Number(_loc1_).toString(36));
      }
      else
      {
         recFood = recFood + new Number(_loc1_).toString(36);
      }
      recFood = recFood + new Number(_loc2_).toString(36);
   }
   if(_loc3_ == "new")
   {
      _loc3_ = game.food.attachMovie("foodMC",foodCounter,foodCounter);
      foodCounter++;
   }
   _loc3_._x = _loc1_ * blockSize;
   _loc3_._y = _loc2_ * blockSize;
   map[_loc1_][_loc2_] = _loc3_;
   _loc3_ = §§pop();
   _loc2_ = §§pop();
   _loc1_ = §§pop();
}
function enterHighscore()
{
   if(score >= lowestHighscore)
   {
      highscores.enterHighscoreMC._visible = true;
      highscores.enterHighscoreMC.focus();
      Key.removeListener(keyListener);
      Key.addListener(enterHighscoreKeyListener);
   }
   else
   {
      loadedRecordingNumber = -1;
      startGame(true);
   }
}
function getLoadedRecordingNumberHighscorePos(num)
{
   var _loc2_ = num;
   var _loc1_ = 0;
   while(true)
   {
      if(_loc1_ < 10)
      {
         if(_loc2_ == highscores[_loc1_].recFile)
         {
            §§push(_loc1_);
            break;
         }
         _loc1_ = _loc1_ + 1;
         continue;
      }
      §§push(undefined);
      break;
   }
   var _loc0_ = _loc2_;
   _loc2_ = _loc1_;
   _loc1_ = §§pop();
   return _loc0_;
}
function loadHighscores()
{
   var _loc3_ = this;
   vars = new LoadVars();
   vars.onLoad = function(success)
   {
      var _loc3_ = this;
      var _loc1_ = 0;
      while(_loc1_ < 10)
      {
         var _loc2_ = highscores.attachMovie("highscoreLine",_loc1_,_loc1_);
         _loc2_._x = 5;
         _loc2_._y = 5 + _loc1_ * 12;
         _loc2_.place.text = _loc1_ + 1 + ".";
         _loc2_.name.text = _loc3_["name" + _loc1_];
         _loc2_.score.text = _loc3_["score" + _loc1_];
         _loc2_.recFile = parseInt(_loc3_["recFile" + _loc1_]);
         _loc1_ = _loc1_ + 1;
      }
      lowestHighscore = parseInt(_loc3_.score9);
      §§push(_loc1_);
      §§push(_loc2_);
      §§push(_loc3_);
      if(!gameRunning)
      {
         loadRecording(random(10));
      }
      false;
      _loc3_ = §§pop();
      _loc2_ = §§pop();
      _loc1_ = §§pop();
   };
   §§push(_loc1_);
   §§push(_loc2_);
   §§push(_loc3_);
   if(_loc3_._url.indexOf("http") != -1)
   {
      vars.load("highscores.txt?" + new Date().getTime());
   }
   else
   {
      vars.load("highscores.txt");
   }
   _loc3_ = §§pop();
   var _loc2_ = §§pop();
   var _loc1_ = §§pop();
}
function loadRecording(num)
{
   var _loc1_ = this;
   vars = new LoadVars();
   vars.onLoad = function(success)
   {
      var _loc1_ = this;
      §§push(_loc1_);
      if(success && _loc1_.recTurn.length)
      {
         recTurn = _loc1_.recTurn;
         recFrame = _loc1_.recFrame;
         recFood = _loc1_.recFood;
         startGame(true);
         false;
      }
      else
      {
         loadRecording((num + 1) % 10);
      }
      _loc1_ = §§pop();
   };
   loadedRecordingNumber = num;
   §§push(_loc1_);
   if(_loc1_._url.indexOf("http") != -1)
   {
      vars.load("rec" + loadedRecordingNumber + ".txt?" + new Date().getTime());
   }
   else
   {
      vars.load("rec" + loadedRecordingNumber + ".txt");
   }
   _loc1_ = §§pop();
}
function saveHighscore()
{
   var _loc1_ = _root;
   sendVars = new LoadVars();
   for(var _loc2_ in _loc1_)
   {
      if(_loc1_[_loc2_] != sendVars)
      {
         sendVars[_loc2_] = _loc1_[_loc2_];
      }
   }
   returnVars = new LoadVars();
   returnVars.onLoad = function()
   {
      if(this.status == "ok")
      {
         loadHighscoresInterval = setInterval(function()
         {
            loadHighscores();
            clearInterval(loadHighscoresInterval);
         }
         ,1000);
      }
      delete sendVars;
      delete this;
   };
   sendVars.sendAndLoad("enterHighscore.php",returnVars,"POST");
   _loc2_;
   _loc1_;
}
function startClicked()
{
   if(!gameRunning || playRec)
   {
      if(highscores.enterHighscoreMC._visible)
      {
         Key.removeListener(enterHighscoreKeyListener);
         Key.addListener(keyListener);
         highscores.enterHighscoreMC._visible = false;
      }
      startGame(false);
   }
}
function viewGame(lineMC)
{
   loadRecording(lineMC.recFile);
   statusTextField.text = "Loading " + lineMC.name.text + "\'s game...";
}
blockSize = 8;
gameHeight = 30;
gameWidth = 45;
replaySpeed = 1;
SNAKE_BLOCK = 1;
xVelocity = [-1,0,1,0];
yVelocity = [0,-1,0,1];
keyListener = new Object();
keyListener.onKeyDown = function()
{
   var _loc1_ = Key.getCode();
   §§push(_loc1_);
   if(_loc1_ > 36 && _loc1_ < 41)
   {
      if(playRec)
      {
         if(_loc1_ == 37 && replaySpeed > 1)
         {
            replaySpeed--;
         }
         else if(_loc1_ == 39 && replaySpeed < 10)
         {
            replaySpeed++;
         }
      }
      else if(game.onEnterFrame != undefined)
      {
         if(_loc1_ - 37 != turnQueue[0])
         {
            turnQueue.unshift(_loc1_ - 37);
         }
      }
   }
   else if(_loc1_ == 32)
   {
      if(!gameRunning || playRec)
      {
         startGame(false);
      }
   }
   else if(_loc1_ == 80)
   {
      if(gameRunning && !playRec)
      {
         if(game.onEnterFrame)
         {
            delete game.onEnterFrame;
            textMC.gotoAndStop("paused");
         }
         else
         {
            game.onEnterFrame = main;
            textMC.gotoAndStop("hide");
         }
      }
   }
   _loc1_ = §§pop();
};
Key.addListener(keyListener);
loadHighscores();
enterHighscoreKeyListener = new Object();
enterHighscoreKeyListener.onKeyDown = function()
{
   if(Key.getCode() == 13)
   {
      playerName = highscores.enterHighscoreMC.nameTextField.text;
      if(playerName == undefined || playerName == "")
      {
         playerName = "no name";
      }
      saveHighscore();
      Key.removeListener(enterHighscoreKeyListener);
      Key.addListener(keyListener);
      highscores.enterHighscoreMC._visible = false;
      loadedRecordingNumber = -1;
      startGame(true);
   }
};
