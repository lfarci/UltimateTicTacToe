--------------------------------------------------------------------------------
-- Contains creation instruction for the Ultimate Tic Tac Toe game.
--
-- Author: Logan Farci (47923)
--------------------------------------------------------------------------------

-- Represents an user that once played the game.
create table USERS (
    pseudonym varchar(20) primary key,
	nvictories int not null,
	nexaequos int not null,
	ndefeats int not null,
        hasUsedJoker int not null
);

-- A pseudonym should be composed of 8 to 20 characters.
ALTER TABLE USERS
ADD CONSTRAINT checkPseudonymLength 
CHECK (LENGTH(pseudonym) >= 8);

-- A pseudonym should be composed of alphabetical characters only.
ALTER TABLE USERS 
ADD CONSTRAINT checkPseudonymNoSpecialCharacters 
CHECK (pseudonym NOT LIKE '%[^a-zA-Z0-9]%');

-- The number of victories should be positive
ALTER TABLE USERS
ADD CONSTRAINT checkNVictoriesPositive
CHECK (nvictories >= 0);

-- The number of ex aequos should be positive
ALTER TABLE USERS
ADD CONSTRAINT checkNExaequosPositive
CHECK (nexaequos >= 0);

-- The number of defeats should be positive
ALTER TABLE USERS
ADD CONSTRAINT checkNDefeatsPositive
CHECK (ndefeats >= 0);

ALTER TABLE USERS
ADD CONSTRAINT checkBooleanHasUsedJoker
CHECK (hasUsedJoker = 0 OR hasUsedJoker = 1);