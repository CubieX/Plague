package com.github.CubieX.Plague;


public class PlagueSchedulerHandler
{
    private Plague pInst = null;
    
    public PlagueSchedulerHandler(Plague pInst)
    {
        this.pInst = pInst;
    }
    
    public void timerTest()
    {
        // DELAYED TASK (only called once)
        pInst.getServer().getScheduler().scheduleSyncDelayedTask(pInst, new Runnable()
        {
            public void run()
            {
                pInst.getServer().broadcastMessage("This message is broadcast by the main thread and will only be shown once after a delay");
            }
         }, 200L);    
        
        // REPEATING TASK WITHIN A SEPERATE TASK (may be sleeped, because it's a seperate task apart from the main server task)
        // it may NOT be used for Bukkit API calls, as most of them are not threat safe!
        
        // RepeatingTask is also available as "syncRepeatingTask". This may be used for Bukkit API calls, but as it blocks the main task, it
        // should be as short blocking as possible!! (can be seen like a "Interrupt" routine)
        // All synch Scheduler Tasks may be called from a asynch task to execute Bukkit API methods safely on the next tick.
        
        int taskID_ART = pInst.getServer().getScheduler().scheduleAsyncRepeatingTask(pInst, new Runnable()
        {// the task ID can be used later to stop the repeating task via "Bukkit.getScheduler().cancelTask(taskID);"
            public void run()
            {
                // The code here may NOT use any Bukkit API calls, as it is NOT thread safe!
                System.out.println("This message is printed by an async thread and will be repeated.");
                
                // make a new scheduler that is running on the main server thread and so can be used to execute a Bukkit API method safely.
                pInst.getServer().getScheduler().scheduleSyncDelayedTask(pInst, new Runnable()
                {
                    public void run()
                    {
                        pInst.getServer().broadcastMessage("This message is broadcast by the main thread trough a async Scheduler that uses a syncDelayd scheduler for it, and will only be shown once after a delay");
                    }
                 }, 1L);
                // ======== End syncDelayed scheduler
            }
         }, 400L, 800L);
        // ========== end asyncRepeating scheduler
    }
    
    /* This is a example of an asynchronous Thread that can be stopped and run.
        boolean running = false;
    ...
    running = true;
    (new Thread()
    {
    @Override
    public void run()
    {
    while(true)
    {
    long startTime = System.currentTimeMillis();
    synchronized(this)
    {
    if(!running)
    return;
    }
    //do stuff
    //pause for 20 milliseconds minus time already taken
    long pauseTime = 20 + startTime - System.currentTimeMillis();
    if(pauseTime <= 0)
    continue;
    try
    {
    Thread.sleep(pauseTime);
    }
    catch(InterruptedException e)
    {
    //this shouldn't happen
    //unless something is wrong
    //or you purposely call "interrupt();"
    return;
    }
    }
    }
    }).start();
    ...
    //when you want to stop it
    synchronized(this)
    {
    running = false;
    }  
    */
    
    
    
    // THIS is a seperate, asynchrnous Thread.
    
    /*
         Thread scheduler = new Thread(new Runnable() {
    @Override
    public void run() {
    while (Thread.currentThread() == scheduler) {// set scheduler to null to stop this thread
    final long delay = 3*60*60*1000; // 3 hours
    final long endTime = System.currentTimeMillis() + delay;
    while (endTime > System.currentTimeMillis()) {// needed in case of spurious wakeups
    try {
    Thread.sleep(endTime - System.currentTimeMillis() + 1);
    } catch (InterruptedException e) {}
    }
     
    // code put here will be repeated every 3 hours
    // if you need to change something on the server (calling Bukikit API), you have to use a syncDelayed scheduler to do so here!
     
    }
    }
    });
    */
}
