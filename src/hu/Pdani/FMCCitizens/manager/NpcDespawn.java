package hu.Pdani.FMCCitizens.manager;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import net.citizensnpcs.api.CitizensAPI;
import org.bukkit.Location;
import org.bukkit.event.Event;

public class NpcDespawn extends Effect {

    static {
        Skript.registerEffect(NpcDespawn.class,"despawn npc [with] id %number-%");
    }

    private Expression<Long> id;

    @Override
    protected void execute(Event e){
        if(id == null || id.getSingle(e) == null)
            return;
        CitizensAPI.getNPCRegistry().getById(id.getSingle(e).intValue()).despawn();
    }

    @Override
    public String toString(Event event, boolean b) {
        return "despawn npc [with] id %number-%";
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        id = (Expression<Long>) e[0];
        return true;
    }
}
