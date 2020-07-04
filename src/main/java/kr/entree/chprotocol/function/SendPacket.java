package kr.entree.chprotocol.function;

import com.laytonsmith.core.Static;
import com.laytonsmith.core.constructs.CVoid;
import com.laytonsmith.core.constructs.Target;
import com.laytonsmith.core.environments.Environment;
import com.laytonsmith.core.exceptions.CRE.CREThrowable;
import com.laytonsmith.core.exceptions.ConfigRuntimeException;
import com.laytonsmith.core.natives.interfaces.Mixed;
import kr.entree.chprotocol.CPacket;
import lombok.val;

import static kr.entree.chprotocol.Mixes.packet;

/**
 * Created by JunHyung Im on 2020-07-05
 */
public class SendPacket extends CHProtocolFunction {
    @Override
    public Class<? extends CREThrowable>[] thrown() {
        return new Class[0];
    }

    @Override
    public Mixed exec(Target t, Environment environment, Mixed... args) throws ConfigRuntimeException {
        val player = args.length >= 2
                ? Static.GetPlayer(args[0], t)
                : Static.getPlayer(environment, t);
        val packet = args.length >= 2
                ? packet(args[1], t)
                : CPacket.create(environment, t);
        packet.send(player, t);
        return CVoid.VOID;
    }

    @Override
    public String getName() {
        return "send_packet";
    }

    @Override
    public Integer[] numArgs() {
        return new Integer[]{1, 2};
    }

    @Override
    public String docs() {
        return "void {player, [packet]} Sends the packet to the given player.";
    }
}
