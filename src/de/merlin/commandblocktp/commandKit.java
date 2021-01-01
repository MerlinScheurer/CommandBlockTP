package de.merlin.commandblocktp;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CommandBlock;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.*;

/**
 * Created by Merlin on 08.02.2017.
 */
public class commandKit implements CommandExecutor {
    private Map<String,String> lastCommandByPlayer = new HashMap<String,String>();

    private Block staredAtBlock(Player player) {
        return player.getTargetBlock((Set<Material>) null, 2000);
    }

    @Override
    public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {

        if (arg1.getLabel().equals("cmdtpset") || arg1.getLabel().equals("/cmdtpset")) {
            String playerName = arg0.getName();
            Player currentPlayer = Bukkit.getPlayer(playerName);

            if (currentPlayer != null) {
                Block blk = staredAtBlock(currentPlayer);

                if (blk.getType() != Material.COMMAND_BLOCK) return false;

                CommandBlock cb = (CommandBlock) blk.getState();
                cb.setCommand(lastCommandByPlayer.get(playerName));
                cb.update();
                arg0.sendMessage("CBT: block updated");
                return true;
            }
            return false;
        } else if (arg1.getLabel().equals("cmdtp") || arg1.getLabel().equals("/cmdtp")) {
            double x, y, z;
            String playerName = arg0.getName();
            Player pl = Bukkit.getPlayer(playerName);
            if (pl != null) {
                x = pl.getLocation().getX();
                y = pl.getLocation().getY();
                z = pl.getLocation().getZ();
                String command = ("/tp @p " + x + " " + y + " " + z);
                lastCommandByPlayer.put(playerName,command);
                arg0.sendMessage(command);
                arg0.sendMessage("CBT: Command set");
            }
            return true;
        }
        return false;
    }

}