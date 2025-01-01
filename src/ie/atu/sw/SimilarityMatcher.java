package ie.atu.sw;

import java.util.List;

public class SimilarityMatcher {

	private VectorSimilarityAlgorithm runAlgorithm(String alg) {
		return switch (alg.toLowerCase()) {
		case "cosine similarity"  -> new CosineSimilarity();
		case "eculidean distance" -> new EuclideanDistance();
		case "dot product"        -> new DotProduct();
		case "combine all"        ->
			new CompositeSimilarity(List.of(new CosineSimilarity(), new EuclideanDistance(), new DotProduct()));
		default                   -> throw new IllegalArgumentException("Invalid similarity algorithm: " + alg);
		};
	}
}
