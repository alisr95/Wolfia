/*
 * Copyright (C) 2016-2019 Dennis Neufeld
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

package space.npstr.wolfia;

import net.dv8tion.jda.bot.sharding.ShardManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import space.npstr.wolfia.commands.CommRegistry;
import space.npstr.wolfia.db.Database;
import space.npstr.wolfia.events.CommandListener;

import static org.assertj.core.api.Assertions.assertThat;

class LauncherTest extends ApplicationTest {

    @Autowired
    private BeanCatcher beanCatcher;

    @Test
    void applicationContextLoads() {
        // smoke test for some usual & important beans
        assertThatContainsBean("commandListener", CommandListener.class);
        assertThatContainsBean("commRegistry", CommRegistry.class);
        assertThatContainsBean("shardManager", ShardManager.class);
        assertThatContainsBean("botContext", BotContext.class);
        assertThatContainsBean("database", Database.class);
    }

    private void assertThatContainsBean(String name, Class<?> clazz) {
        var beans = this.beanCatcher.getBeans();
        assertThat(beans).containsKey(name);
        assertThat(beans.get(name)).isInstanceOf(clazz);
    }

}
