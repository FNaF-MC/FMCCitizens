package hu.Pdani.FMCCitizens.manager;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import hu.Pdani.FMCCitizens.registry.DeleteNpc;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.util.PlayerAnimation;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public class NpcNameplate extends Effect {

    static {
        Skript.registerEffect(NpcNameplate.class,"change npc nameplate [with] id %-number% to %-boolean%");
    }

    private Expression<Long> id;
    private Expression<Boolean> bval;

    @Override
    protected void execute(Event e){
        if(id == null || id.getSingle(e) == null)
            return;
        if(bval == null || bval.getSingle(e) == null)
            return;
        NPC target = CitizensAPI.getNPCRegistry().getById(id.getSingle(e).intValue());
        if(target == null || !target.isSpawned())
            return;
        target.getEntity().setCustomNameVisible(bval.getSingle(e));
    }

    @Override
    public String toString(Event event, boolean b) {
        return "change npc nameplate [with] id %-number% to %-boolean%";
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        id = (Expression<Long>) e[0];
        bval = (Expression<Boolean>) e[1];
        return true;
    }
}
