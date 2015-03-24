/**
* From the book 'Mapping and Visualization in SuperCollider'
* by Marinos Koutsomichalis.
*/

SackInclude { // class to evaluate code from other files.

	// Absolute (full) path
	*absolute{ arg path;
		path.loadPaths;
	}

	// Relative (relative to the current directory) path
	*relative{ arg path;
		var pathToLoad = (PathName(thisProcess.nowExecutingPath)).pathOnly +/+ path;
		pathToLoad.loadPaths; // evaluate code in file
	}

	// As relative but load as in executes what is in the file
	*load{ arg path;
		^((PathName(thisProcess.nowExecutingPath)).pathOnly +/+ path).load;
	}
}