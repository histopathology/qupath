/*-
 * #%L
 * This file is part of QuPath.
 * %%
 * Copyright (C) 2018 - 2022 QuPath developers, The University of Edinburgh
 * %%
 * QuPath is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * QuPath is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License 
 * along with QuPath.  If not, see <https://www.gnu.org/licenses/>.
 * #L%
 */

package qupath.lib.gui.actions.menus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.controlsfx.control.action.Action;

import javafx.scene.control.Menu;
import qupath.lib.gui.QuPathGUI;


public class Menus {
	
	private QuPathGUI qupath;
	
	private List<Action> actions;
	
	public Menus(QuPathGUI qupath) {
		this.qupath = qupath;
	}
	
	
	
	public synchronized Collection<Action> getActions() {
		if (actions == null) {
			actions = new ArrayList<>();
			for (var builder : Arrays.asList(
					new FileMenuActions(qupath),
					new EditMenuActions(qupath),
					new ObjectsMenuActions(qupath),
					new ViewMenuActions(qupath),
					new MeasureMenuActions(qupath),
					new AutomateMenuActions(qupath),
					new AnalyzeMenuActions(qupath),
					new TMAMenuActions(qupath),
					new ClassifyMenuActions(qupath),
					new ExtensionsMenuActions(qupath),
					new HelpMenuActions(qupath)
					)) {
				actions.addAll(builder.getActions());
			}
			
		}
		return actions;
	}
	

}