/*
 * Copyright 2012-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.spring.start.site.extension.dependency.springdata;

import io.spring.initializr.generator.buildsystem.Build;
import io.spring.initializr.generator.buildsystem.Dependency;
import io.spring.initializr.generator.buildsystem.DependencyContainer;
import io.spring.initializr.generator.buildsystem.DependencyScope;
import io.spring.initializr.generator.spring.build.BuildCustomizer;

/**
 * A {@link BuildCustomizer} for R2DBC that adds the necessary extra dependencies based on
 * the selected driver.
 *
 * @author Stephane Nicoll
 */
public class R2dbcBuildCustomizer implements BuildCustomizer<Build> {

	@Override
	public void customize(Build build) {
		if (build.dependencies().has("h2")) {
			addCoreDriver(build.dependencies(), "r2dbc-h2");
		}
		if (build.dependencies().has("mysql")) {
			addCoreDriver(build.dependencies(), "r2dbc-mysql");
		}
		if (build.dependencies().has("postgresql")) {
			addCoreDriver(build.dependencies(), "r2dbc-postgresql");
		}
		if (build.dependencies().has("sqlserver")) {
			addCoreDriver(build.dependencies(), "r2dbc-mssql");
		}
	}

	private void addCoreDriver(DependencyContainer dependencies, String id) {
		dependencies.add(id, Dependency.withCoordinates("io.r2dbc", id).scope(DependencyScope.RUNTIME));
	}

}
