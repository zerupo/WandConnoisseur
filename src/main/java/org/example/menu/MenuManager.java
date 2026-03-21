package org.example.menu;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

class MenuEntry{
    public Menu menu;
    public long accessTime;

    public MenuEntry(Menu menu){
        this.menu = menu;
        this.accessTime = System.currentTimeMillis();
    }

    public void updateAccessTime(){
        this.accessTime = System.currentTimeMillis();
    }
}

public class MenuManager{
    private final Map<String, MenuEntry> map = new HashMap<>();
    private final long accessTimeLimit = 24*60*60*1000; // 24 hours
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public MenuManager(){
        this.scheduler.scheduleAtFixedRate(this::clean, this.accessTimeLimit, this.accessTimeLimit, TimeUnit.MILLISECONDS);
    }

    public void add(Menu menu){
        this.map.put(menu.getId(), new MenuEntry(menu));
    }

    public Menu getById(String id){
        MenuEntry entry = this.map.get(id);

        if(entry == null){
            return null;
        }

        entry.updateAccessTime();
        return entry.menu;
    }

    private void clean(){
        long currentTime = System.currentTimeMillis();
        Iterator<Map.Entry<String, MenuEntry>> iterator = this.map.entrySet().iterator();

        while(iterator.hasNext()){
            Map.Entry<String, MenuEntry> entry = iterator.next();
            MenuEntry menuEntry = entry.getValue();

            if(currentTime - menuEntry.accessTime > this.accessTimeLimit){
                menuEntry.menu.deleteFiles();
                iterator.remove();
                System.out.println("Menu with ID " + entry.getKey() + " has been removed due to timeout.");
            }
        }
    }
}