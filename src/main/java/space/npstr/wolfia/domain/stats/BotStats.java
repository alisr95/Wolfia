/*
 * Copyright (C) 2016-2020 the original author or authors
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

package space.npstr.wolfia.domain.stats;

import java.util.List;

import static org.immutables.value.Value.Immutable;

@Immutable
@StatsStyle
public interface BotStats {

    /**
     * @return average player size for all games
     */
    Number averagePlayerSize();

    /**
     * @return win stats for all games
     */
    WinStats totalWinStats();

    /**
     * @return win stats for games by player size
     */
    List<WinStats> winStatsByPlayerSize();
}
