package de.merlin.commandblocktp;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CommandBlock;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Set;

/**
 * Created by Merlin on 08.02.2017.
 */
public class commandKit implements CommandExecutor {
    private String lastCommand = null;

    public String getLastCommand(){
        return lastCommand;
    }
    private Block staredAtBlock(Player player){
        return player.getTargetBlock((Set<Material>) null,2000);
    }

    @Override
    public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {

        if(arg1.getLabel().equals("cmdtpset") || arg1.getLabel().equals("/cmdtpset")) {
            Block blk = staredAtBlock(Bukkit.getPlayer(arg0.getName()));
            if(blk.getType() != Material.COMMAND) return false;

            CommandBlock cb = (CommandBlock) blk.getState();
            cb.setCommand(getLastCommand());
            cb.update();
            arg0.sendMessage("CBT: block updated");

        }else if(arg1.getLabel().equals("cmdtp") || arg1.getLabel().equals("/cmdtp")) {
            double x, y, z;
            Player pl = Bukkit.getPlayer(arg0.getName());
            x = pl.getLocation().getX();
            y = pl.getLocation().getY();
            z = pl.getLocation().getZ();
            lastCommand = ("/tp @p " + x + " " + y + " " + z + " ");
            arg0.sendMessage(lastCommand);
            arg0.sendMessage("CBT: Command set");
        }
        return false;
    }

}