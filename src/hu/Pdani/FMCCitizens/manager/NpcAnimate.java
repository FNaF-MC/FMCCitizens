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

public class NpcAnimate extends Effect {

    static {
        Skript.registerEffect(DeleteNpc.class,"animate npc [with] id %number-% [with] animation %string-%");
    }

    private Expression<Long> id;
    private Expression<String> anim;

    @Override
    protected void execute(Event e){
        if(id == null || id.getSingle(e) == null)
            return;
        if(anim == null || anim.getSingle(e) == null)
            return;
        NPC target = CitizensAPI.getNPCRegistry().getById(id.getSingle(e).intValue());
        if(target.getEntity().getType() != EntityType.PLAYER)
            return;
        PlayerAnimation pa = null;
        try {
            pa = PlayerAnimation.valueOf(anim.getSingle(e).toUpperCase());
        } catch (IllegalArgumentException ignored){}
        if(pa == null)
            return;
        pa.play((Player)target.getEntity());
    }

    @Override
    public String toString(Event event, boolean b) {
        return "animate npc [with] id %number-% [with] animation %string-%";
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        id = (Expression<Long>) e[0];
        anim = (Expression<String>) e[1];
        return true;
    }
}
