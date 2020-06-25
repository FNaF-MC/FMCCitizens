package hu.Pdani.FMCCitizens.registry;

import ch.njol.skript.Skript;
import ch.njol.skript.entity.EntityType;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import hu.Pdani.FMCCitizens.FMCCitizensPlugin;
import net.citizensnpcs.api.CitizensAPI;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;

public class CreateNpc extends SimpleExpression<Number> {

    static {
        Skript.registerExpression(CreateNpc.class,Number.class, ExpressionType.COMBINED,"[create] new npc [with] type %-entitytype% and name %-string%");
    }

    private Expression<String> name;
    private Expression<ch.njol.skript.entity.EntityType> type;

    @Override
    protected Number[] get(Event e) {
        if(name == null || name.getSingle(e) == null)
            return null;
        if(type == null || type.getSingle(e) == null)
            return null;
        String npc = name.getSingle(e);
        EntityType etype = type.getSingle(e);
        org.bukkit.entity.EntityType spawner;
        try {
            spawner = org.bukkit.entity.EntityType.valueOf(etype.toString().toUpperCase());
        }catch (IllegalArgumentException ex){
            return null;
        }
        int id = CitizensAPI.getNPCRegistry().createNPC(spawner,npc).getId();
        return new Number[]{id};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Number> getReturnType() {
        return Number.class;
    }

    @Override
    public String toString(Event event, boolean b) {
        return "[create] new npc [with] type %-entitytype% and name %-string%";
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        type = (Expression<ch.njol.skript.entity.EntityType>) e[0];
        name = (Expression<String>) e[1];
        return true;
    }
}
