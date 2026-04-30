package org.example.main;

import org.example.script.Script;

import java.util.*;
import java.util.function.Predicate;
import org.reflections.Reflections;

public class ScriptList{
    private final Script[] scripts;

    public ScriptList(Predicate<Script> filter, Comparator<Script> comparator){
        Reflections reflections = new Reflections("org.example.script");
        Set<Class<? extends Script>> subclasses = reflections.getSubTypesOf(Script.class);
        List<Script> ScriptList = new ArrayList<>();

        for(Class<? extends Script> clazz : subclasses){
            try{
                Script script = clazz.getDeclaredConstructor().newInstance();
                if(filter.test(script)){
                    ScriptList.add(script);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        this.scripts = ScriptList.toArray(new Script[0]);
        Arrays.sort(this.scripts, comparator);
    }

    public ScriptList(Predicate<Script> filter){
        this(filter, Comparator.comparing(Script::getName));
    }

    public ScriptList(Comparator<Script> comparator){
        this(script -> true, comparator);
    }

    public ScriptList(){
        this(script -> true, Comparator.comparing(Script::getName));
    }

    public Script[] getScripts(boolean cloning){
        if(!cloning){
            return this.scripts;
        }

        Script[] result = new Script[this.scripts.length];
        for(int i=0; i < result.length; i++){
            result[i] = this.scripts[i].clone();
        }
        return result;
    }
}