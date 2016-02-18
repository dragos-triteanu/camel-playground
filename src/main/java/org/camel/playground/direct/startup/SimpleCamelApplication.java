/*
 * Copyright (C) Scott Cranton and Jakub Korab
 * https://github.com/CamelCookbook
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.camel.playground.direct.startup;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.SimpleRegistry;
import org.camel.playground.direct.routes.SomeRoute;
import org.camel.playground.direct.beans.SomeBean;

/**
 * Example that demonstrates how to run the Camel runtime in a standalone Java application.
 */
public class SimpleCamelApplication {
    public static void main(String[] args) throws Exception {
        SimpleRegistry registry = new SimpleRegistry();
        // add POJOs to the registry here using registry.put("name", <object reference>)

        registry.put("someBean",new SomeBean());

        CamelContext context = new DefaultCamelContext(registry);

        context.addRoutes(new SomeRoute());

        context.start();

        // let the Camel runtime do its job for 5 seconds
        Thread.sleep(5000);

        // shutdown
        context.stop();
    }
}
