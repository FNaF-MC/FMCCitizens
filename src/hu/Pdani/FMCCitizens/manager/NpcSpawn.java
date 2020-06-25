package hu.Pdani.FMCCitizens.manager;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import hu.Pdani.FMCCitizens.FMCCitizensPlugin;
import net.citizensnpcs.api.CitizensAPI;
import org.bukkit.Location;
import org.bukkit.event.Event;

public class NpcSpawn extends Effect {

    static {
        Skript.registerEffect(NpcSpawn.class,"spawn npc [with] id %-number% [at] location %-location%");
    }

    private Expression<Long> id;
    private Expression<Location> loc;

    @Override
    protected void execute(Event e){
        if(id == null || id.getSingle(e) == null)
            return;
        if(loc == null || loc.getSingle(e) == null)
            return;
        CitizensAPI.getNPCRegistry().getById(id.getSingle(e).intValue()).spawn(loc.getSingle(e));
    }

    @Override
    public String toString(Event event, boolean b) {
        return "spawn npc [with] id %-number% [at] location %-location%";
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        id = (Expression<Long>) e[0];
        loc = (Expression<Location>) e[1];
        return true;
    }
}
