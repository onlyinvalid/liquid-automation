package org.liquidbot.bot.script.api.wrappers.definitions;

import org.liquidbot.bot.client.parser.HookReader;
import org.liquidbot.bot.client.reflection.Reflection;

import java.util.Hashtable;

/*
 * Created by Hiasat on 7/31/14
 */
public class ObjectDefinition {

    private static Hashtable<Integer, String> nameCache = new Hashtable<>();
    private static Hashtable<Integer, String[]> actionsCache = new Hashtable<>();

    private String name;
    private String[] actions;

    public ObjectDefinition(int Id) {
        if (nameCache.get(Id) == null) {
            Object raw = Reflection.invoke("Client#getGameObjectComposite()", null, Id, HookReader.methods.get("Client#getGameObjectComposite()").getCorrectParam());
            nameCache.put(Id, (String) Reflection.value("GameObjectComposite#getName()", raw));
            actionsCache.put(Id, (String[]) Reflection.value("GameObjectComposite#getActions()", raw));
        }
        if (nameCache.containsKey(Id)) {
            name = nameCache.get(Id);
            actions = actionsCache.get(Id);
        }
    }

    /**
     * Object Name
     *
     * @return String: Object Name
     */
    public String getName() {
        return name;
    }

    /**
     * Object Actions
     *
     * @return String[] : Object Interact Actions
     */
    public String[] getActions(){
        return actions;
    }

    /**
     * check if Object composite is null or not
     *
     * @return Boolean : return true if not null else false
     */
    public boolean isValid() {
        return name != null;
    }
}