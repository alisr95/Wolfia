/*
 * Copyright (C) 2017 Dennis Neufeld
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package space.npstr.wolfia.game;

import space.npstr.wolfia.db.entity.CachedUser;
import space.npstr.wolfia.game.definitions.Alignments;
import space.npstr.wolfia.game.definitions.Roles;
import space.npstr.wolfia.game.exceptions.IllegalGameStateException;
import space.npstr.wolfia.utils.discord.Emojis;
import space.npstr.wolfia.utils.discord.TextchatUtils;

/**
 * Created by napster on 05.07.17.
 * <p>
 * Representing a player in a game
 */
public class Player {

    public final long userId;
    public final long guildId; //guild where the game is running in
    public final Alignments alignment;
    public final Roles role;
    public final int number;

    private boolean isAlive = true;

    public Player(final long userId, final long guildId, final Alignments alignment, final Roles role, final int number) {
        this.userId = userId;
        this.guildId = guildId;
        this.alignment = alignment;
        this.role = role;
        this.number = number;
    }

    public long getUserId() {
        return this.userId;
    }

    public boolean isAlive() {
        return this.isAlive;
    }

    public boolean isBaddie() {
        return this.alignment == Alignments.WOLF;
    }

    public boolean isGoodie() {
        return this.alignment == Alignments.VILLAGE;
    }

    public String getName() {
        return CachedUser.get(this.userId).getName();
    }

    public String getNick() {
        return CachedUser.get(this.userId).getNick(this.guildId);
    }

    public String getBothNamesFormatted() {
        final CachedUser cu = CachedUser.get(this.userId);
        return "**" + cu.getName() + "** aka **" + cu.getNick(this.guildId) + "**";//todo escape these from markdown characters to ensure proper formatting
    }

    public String asMention() {
        return TextchatUtils.userAsMention(this.userId);
    }

    /**
     * @return an emoji representing role and alignment of this player
     */
    public String getCharacterEmoji() {
        //role specific ones
        if (this.role == Roles.COP) {
            return Emojis.MAGNIFIER;
        }
        //alignment specific ones
        if (this.alignment == Alignments.WOLF) {
            return Emojis.SPY;
        }
        return Emojis.COWBOY;
    }

    public void kill() throws IllegalGameStateException {
        if (!this.isAlive) {
            throw new IllegalGameStateException("Can't kill a dead player");
        }
        this.isAlive = false;
    }
}
