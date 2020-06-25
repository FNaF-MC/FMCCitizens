package hu.Pdani.FMCCitizens.manager;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Location;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerTeleportEvent;

public class NpcTeleport extends Effect {

    static {
        Skript.registerEffect(NpcTeleport.class,"teleport npc [with] id %-number% [to] location %-location%");
    }

    private Expression<Long> id;
    private Expression<Location> loc;

    @Override
    protected void execute(Event e){
        if(id == null || id.getSingle(e) == null)
            return;
        if(loc == null || loc.getSingle(e) == null)
            return;
        NPC target = CitizensAPI.getNPCRegistry().getById(id.getSingle(e).intValue());
        if(target == null || !target.isSpawned())
            return;
        target.teleport(loc.getSingle(e), PlayerTeleportEvent.TeleportCause.PLUGIN);
    }

    @Override
    public String toString(Event event, boolean b) {
        return "teleport npc [with] id %-number% [to] location %-location%";
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        id = (Expression<Long>) e[0];
        loc = (Expression<Location>) e[1];
        return true;
    }
}
