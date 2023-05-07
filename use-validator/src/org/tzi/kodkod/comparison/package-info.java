/**
 * Package for functionality for comparing instance finder configurations.
 * 
 * Use
 * {@link org.tzi.kodkod.comparison.SettingsConfigurationComparator#compareSettingsConfigurations(org.tzi.use.kodkod.plugin.gui.model.data.SettingsConfiguration, org.tzi.use.kodkod.plugin.gui.model.data.SettingsConfiguration)}
 * to compare instance finder configurations.
 * 
 * The implemented feature provides the atomised comparison of selected types of
 * configuration details. Conceptually, different classes of comparison results
 * are defined. Each complete comparison result and also each underlying
 * comparison result of configuration parts is typified. Therefor information
 * about each comparison of each configuration detail is given, but also the
 * classification in the impact on the comparison of the overall configuration.
 * Not all types of configuration details are respected in the comparison. Some
 * are ignored. This is contained in the information, given with the result of a
 * comparison. {@link org.tzi.kodkod.comparison.ComparisonResult} defines
 * possible results for configuration details.
 * 
 * Additionally it is worth noting that with the information the feature gives,
 * also advanced comparison aspects become easily visible. It is possible to see
 * whether one configuration represents a search space that is a subset of the
 * one represented by another configuration. Also overlappings of the search
 * spaces are easy to identify.
 * 
 * @author Jan Prien
 * 
 */
package org.tzi.kodkod.comparison;
