### Background
Sometimes items cannot be shipped to certain zip codes, and the rules for these restrictions are stored as a series of ranges of 5 digit codes. For example if the ranges are:

[94133,94133] [94200,94299] [94600,94699]

Then the item can be shipped to zip code 94199, 94300, and 65532, but cannot be shipped to 94133, 94650, 94230, 94600, or 94299.

Any item might be restricted based on multiple sets of these ranges obtained from multiple sources.

### Problem
Given a collection of 5-digit ZIP code ranges (each range includes both their upper and lower bounds), provide an algorithm that produces the minimum number of ranges required to represent the same restrictions as the input.

### Notes
- The ranges above are just examples, your implementation should work for any set of arbitrary ranges
- Ranges may be provided in arbitrary order
- Ranges may or may not overlap
- Your solution will be evaluated on the correctness and the approach taken, and adherence to coding standards and best practices

### Examples:
If the input = [94133,94133] [94200,94299] [94600,94699]
Then the output should be = [94133,94133] [94200,94299] [94600,94699]

If the input = [94133,94133] [94200,94299] [94226,94399] 
Then the output should be = [94133,94133] [94200,94399]

### Evaluation Guidelines:
Your work will be evaluated against the following criteria:
- Successful implementation
- Efficiency of the implementation
- Design choices and overall code organization
- Code quality and best practices

### How to run the program

1. Input needs to be passed in a file in following format. The file name needs to passed in input args when running the program.
	A sample file is present in src/main/resources folder with name "Success_PartialOverlap.txt" <br/>
		94084,94087 <br/>
		94080,94085 <br/>
		94000,94001 <br/>
		10001,10009 <br/>
		10005,10009 <br/>
		10000,10001 <br/>
		10010,10099 <br/>
		
### Assumptions

1. The zip codes should be a positive integer
2. The zip code range should have lower zip code first, followed by higher zip code.
