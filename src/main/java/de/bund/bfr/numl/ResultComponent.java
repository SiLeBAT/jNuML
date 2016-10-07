/*******************************************************************************
 * Copyright (c) 2015 Federal Institute for Risk Assessment (BfR), Germany
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Contributors:
 *     Department Biological Safety - BfR
 *******************************************************************************/
package de.bund.bfr.numl;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.google.common.base.Strings;

public class ResultComponent extends NMBase {

	protected static final String ELEMENT_NAME = "resultComponent";

	private static final String ID = "id";
	private static final String DIMENSION_DESCRIPTION = "dimensionDescription";
	private static final String DIMENSION = "dimension";

	private String id;
	private DimensionDescription dimensionDescription;
	private List<DimensionValue> dimension;

	public ResultComponent(String id, DimensionDescription dimensionDescription, List<DimensionValue> dimension) {
		this.id = id;
		this.dimensionDescription = dimensionDescription;
		this.dimension = dimension;
	}

	protected ResultComponent(Element node, List<OntologyTerm> ontologyTerms) {
		super(node);

		id = Strings.emptyToNull(node.getAttribute(ID));
		dimensionDescription = null;
		dimension = new ArrayList<>();

		for (Element child : Utils.getChildren(node)) {
			switch (child.getNodeName()) {
			case DIMENSION_DESCRIPTION:
				for (Element childChild : Utils.getChildren(child)) {
					DimensionDescription description = DimensionDescription.createDescription(childChild,
							ontologyTerms);

					if (description != null) {
						dimensionDescription = description;
						break;
					}
				}
				break;
			case DIMENSION:
				for (Element childChild : Utils.getChildren(child)) {
					DimensionValue value = DimensionValue.createValue(childChild, dimensionDescription);

					if (value != null) {
						dimension.add(value);
					}
				}
			}
		}
	}

	public String getId() {
		return id;
	}

	public DimensionDescription getDimensionDescription() {
		return dimensionDescription;
	}

	public List<DimensionValue> getDimension() {
		return dimension;
	}

	@Override
	public String toString() {
		return "ResultComponent [id=" + id + ", dimensionDescription=" + dimensionDescription + ", dimension="
				+ dimension + ", metaId=" + metaId + "]";
	}

	@Override
	protected Element toNode(Document doc) {
		Element node = doc.createElement(ELEMENT_NAME);
		Element descriptionNode = doc.createElement(DIMENSION_DESCRIPTION);
		Element dimensionNode = doc.createElement(DIMENSION);

		descriptionNode.appendChild(dimensionDescription.toNode(doc));

		for (DimensionValue value : dimension) {
			dimensionNode.appendChild(value.toNode(doc));
		}

		Utils.setAttributeValue(node, ID, id);
		node.appendChild(descriptionNode);
		node.appendChild(dimensionNode);
		updateNode(node);

		return node;
	}
}
