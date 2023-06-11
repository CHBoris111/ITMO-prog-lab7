package src.Command.ConcreteCommands;

import src.Command.Command;
import src.Command.Receiver;
import src.Command.ClientReceiver;
import src.User.User;

public class MaxByMeleeWeapon extends Command{
    public MaxByMeleeWeapon()
    {}
    public MaxByMeleeWeapon(Receiver commandReceiver, ClientReceiver clientReceiver) {
        this.commandReceiver = commandReceiver;
        this.clientReceiver = clientReceiver;
    }

    @Override
    public void execute(User user) {
        commandReceiver.max_by_melee_weapon();
    }

    @Override
    public Command clientExecute() {
        return clientReceiver.max_by_melee_weapon();
    }

    @Override
    protected String writeInfo() {
        return "Command.MaxByMeleeWeapon.writeInfo";
    }
}
