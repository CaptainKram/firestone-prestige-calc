# firestone-prestige-calc
This helps with the optimal time to prestige. It is based on the calculations in the wiki by Fasp3000 link: https://firestone-idle-rpg.fandom.com/wiki/Temple_of_Eternals  

When you add bonuses you have under Prestigious Bonus, only add Prestigious, not Firestone Effect Bonuses. Those bonuses go into the "prestigiousMap".  
The main value to look at is the coefficient of viability. If it grows - that means you should stay put and not prestige. To check weather it grows - adjust values after some time, run the program again and see the number.  
If you are close to an upgrade, even if it is going down - check how things are after you adjust farm stage and the new upgrade.  
In case the coefficient is still lower than max - you should probably prestige.  

Other than the prestigious bonuses you should adjust the 5 values below the prestigiousMap per run, first 2 change only at run start:  
START_OF_THIS_RUN - the time in hours:minutes for when this run started.  
FIRESTONES_WE_HAVE - firestones at top right in the temple of eternals  
PRESTIGE_MULTIPLIER - raining gold % in temple of eternals  
TOTAL_GOLD - total gold earned, which you can see in character (c) statistics  
GOLD_PER_HOUR - this can be seen for offline by hovering on a gold item or you can track it for 5 min and then multiply by 12 for current farm stage while online.  
