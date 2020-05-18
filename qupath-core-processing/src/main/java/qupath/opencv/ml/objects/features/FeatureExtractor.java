package qupath.opencv.ml.objects.features;

import java.nio.FloatBuffer;
import java.util.Collection;
import java.util.List;

import qupath.lib.classifiers.object.ObjectClassifier;
import qupath.lib.images.ImageData;
import qupath.lib.objects.PathObject;

/**
 * Interface for extracting features from {@linkplain PathObject PathObjects} for the purpose of object classification.
 * 
 * @author Pete Bankhead
 *
 * @param <T> generic parameter related to the {@link ImageData} (not always required).
 * @see ObjectClassifier
 */
public interface FeatureExtractor<T> {
	
	/**
	 * @return the names of the features that can be extracted
	 */
	public List<String> getFeatureNames();
	
	/**
	 * @return the number of features that can be extracted
	 */
	public int nFeatures();

	/**
	 * Extract features from a collection of objects, adding the corresponding values to a {@link FloatBuffer}.
	 * Each feature is a single numeric value. At the end, {@code pathObjects.size() * nFeatures()} features should be added 
	 * to the buffer. Each object is processed in turn, with all features for that object added before the next object is processed.
	 * 
	 * @param imageData image data, used by some implementations to determine feature values (e.g. if these depend upon pixels).
	 * @param pathObjects objects for which features should be calculated
	 * @param buffer buffer into which feature values should be added
	 */
	public void extractFeatures(ImageData<T> imageData, Collection<? extends PathObject> pathObjects, FloatBuffer buffer);
	
	/**
	 * Check for missing features, returning the names.
	 * This is useful as a warning that the input for the feature extractor may not be valid.
	 * Default implementation returns an empty list; however, implementations should attempt to provide a meaningful 
	 * output if possible.
	 * @param imageData image containing the objects to test
	 * @param pathObject object to test for missing feature
	 * @return a collection of feature names that correspond to missing features
	 */
	Collection<String> getMissingFeatures(ImageData<T> imageData, PathObject pathObject);
}