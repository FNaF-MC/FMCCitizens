package hu.Pdani.FMCCitizens.registry;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import net.citizensnpcs.api.CitizensAPI;
import org.bukkit.entity.EntityType;
import org.bukkit.event.Event;

public class CreateNpc extends SimpleExpression<Integer> {

    static {
        Skript.registerExpression(CreateNpc.class,Integer.class, ExpressionType.COMBINED,"[create] npc [with] type %entitytype-% and name %string-%");
    }

    private Expression<String> name;
    private Expression<EntityType> type;

    @Override
    protected Integer[] get(Event e) {
        if(name == null || name.getSingle(e) == null)
            return null;
        if(type == null || type.getSingle(e) == null)
            return null;
        String npc = name.getSingle(e);
        EntityType etype = type.getSingle(e);
        int id = CitizensAPI.getNPCRegistry().createNPC(etype,npc).getId();
        return new Integer[id];
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Integer> getReturnType() {
        return null;
    }

    @Override
    public String toString(Event event, boolean b) {
        return "[create] npc [with] type %entitytype-% and name %string-%";
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        type = (Expression<EntityType>) e[0];
        name = (Expression<String>) e[1];
        return true;
    }
}
