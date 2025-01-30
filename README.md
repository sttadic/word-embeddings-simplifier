# Text Simplifier with Word Embeddings
This is console-based Java application that simplifies text by replacing complex or uncommon words with simpler alternatives. It leverages *word embeddings* and *vector similarity algorithms* to find appropriate replacements, and ensures the simplified text retains its original structure.

<br>

## Usage
#### Prerequisites: Java 19 or higher (virtual threads support).
- Clone the repository:
```bash
git clone https://github.com/sttadic/word-embeddings-simplifier.git
```

- Run the application (compiled):
```bash
java ie.atu.sw.Runner
```
<br>

## Predefined Resources
- **Word Embeddings**:  *embeddings.txt* <br>
Pre-processed GloVe embeddings, reduced to 60,100 words for optimized performance.
- **Most Common Words**:  *google-1000.txt* <br>
A list of 1,000 most frequently used words in English.
#### These resources are included in the repository for convenience. However, you can replace them with your own files as long as they follow the same structure.

<br>

## Example Workflow
1. Specify file paths for word embeddings, input, output and the most common words, or use the defaults.
2. Choose from three vector similarity algorithms, or use combined average score to calculate similarities.
3. Start the application process.
4. View the simplified text in the output file.

<br>

## Vector Similarity Algorithms
- *Cosine Similarity* - measures the cosine of the angle between two vectors in a multi-dimensional space, resulting in a score between -1 (opposite direction) and 1 (identical direction).
- *Euclidean Distance* - calculates the straight-line distance between vectors (points) in a multi-dimensional space.
- *Dot Product* - computes the scalar value that represents the sum of the products of corresponding elements in two vectors.
- *Combined Average* - averages the results of multiple algorithms.

<br>

## Features
> **Menu-Driven User Interface** <br>
- The application provides an intuitive, interactive menu that allows users to configure settings and initiate text simplification process. Users can specify paths for word embeddings, common words, and input/output files or relay on default settings. Additionally, the menu enables users to select a vector similarity algorithm, choosing from *Cosine Similarity*, *Euclidean Distance*, and *Dot Product*, or a *combined average* of these methods to calculate similarity scores required for the simplification process.

> **Extensive Error Handling** <br>
- The application delivers user-friendly error messages within a menu for invalid inputs, missing files, unsupported formats, unequal vector lengths, issues during file reading and writing, and other exceptions. It also handles runtime errors such as division by zero or un-processable lines, ensuring minimal disruption to the user experience.

> **Multithreading** <br>
- The application employs concurrency using *Virtual Threads* and *Executor Service* for processing word embeddings.

> **Singleton Pattern** <br> 
- The Singleton Design Pattern is utilized in the *SimplifierManager* class to ensure that a single instance manages the simplification process, even if an error returns control to the menu, thus preventing unnecessary instantiations.

> **Preservation of Text Structure** <br>
- The Tokenizer class plays a key role in preserving the input text structure, such as punctuation and spaces. Its functionality ensures that these elements are accurately maintained in the output, contributing to readability and correctness in the simplified text.

> **Abstractions and Polymorphism** <br> 
- The application adheres to *Object-Oriented Design Principles*, including *Single Responsibility Principle (SRP)* and *Separation of Concerns (SoC)*, to maintain a clean and modular codebase. Functionality is divided into dedicated classes, such as parsers, similarity algorithms, input/output handlers, each serving a well-defined purpose.<br>Key abstractions, such as *interfaces*, *abstract classes*, *records* and *utility classes* (e.g., *FileParser, VectorSimilarityAlgorithm, SimplifierConfig, VectorUtils*) enhance flexibility, loose coupling, and maintainability.
- Polymorphism is leveraged, particularly in the *SimilarityFinder* class, where the appropriate *VectorSimilarityAlgorithm* implementation is dynamically selected at runtime based on user input, ensuring the system remains scalable and flexible enabling integration of new algorithms or features.
