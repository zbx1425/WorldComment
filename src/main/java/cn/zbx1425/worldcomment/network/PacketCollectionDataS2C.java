package cn.zbx1425.worldcomment.network;

import cn.zbx1425.worldcomment.Main;
import cn.zbx1425.worldcomment.platform.ServerPlatform;
import cn.zbx1425.worldcomment.data.CommentEntry;
import cn.zbx1425.worldcomment.data.network.ImageDump;
import cn.zbx1425.worldcomment.gui.CommentListScreen;
import cn.zbx1425.worldcomment.platform.multiver.MinecraftClientShim;
import io.netty.buffer.Unpooled;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;

import java.util.ArrayList;
import java.util.List;

public class PacketCollectionDataS2C {

    public static final Identifier IDENTIFIER = Main.id("collection_data");

    public static void send(ServerPlayer target, List<CommentEntry> data, long nonce) {
        FriendlyByteBuf buffer = new FriendlyByteBuf(Unpooled.buffer());
        buffer.writeLong(nonce);
        buffer.writeInt(data.size());
        for (CommentEntry comment : data) {
            buffer.writeIdentifier(comment.level);
            comment.streamWrite(buffer);
        }
        ServerPlatform.sendPacketToPlayer(target, IDENTIFIER, buffer);
    }

    public static class ClientLogics {

        public static void handle(FriendlyByteBuf buffer) {
            long nonce = buffer.readLong();
            int commentSize = buffer.readInt();
            ArrayList<CommentEntry> comments = new ArrayList<>(commentSize);
            for (int j = 0; j < commentSize; j++) {
                Identifier level = buffer.readIdentifier();
                CommentEntry comment = new CommentEntry(level, buffer);
                if (comment.deleted) continue;
                comments.add(comment);
            }

            if (MinecraftClientShim.guiGetScreen() instanceof CommentListScreen) {
                ((CommentListScreen)MinecraftClientShim.guiGetScreen()).handleCommentDataUI(comments, nonce, commentSize);
            }

            ImageDump.handleDumpResponse(comments, nonce);
        }
    }
}
