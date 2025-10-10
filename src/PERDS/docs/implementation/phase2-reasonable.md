# Phase 1: Basic Implementation (40-49)

## Goal
Create a basic emergency network with simple incident registration and nearest-unit allocation.

## Date Started: [06/10/2025]

## Tasks
- [ done ] Incident queue class and dispatcher class
- [ done ] Sort queue via severity 
- [ done ] Find nearest unit and dispatch units based on severity , mark as unavailable afterwards
- [ done ] (in the main file of the core package) ] Write basic tests


## Code organisation and testing

- 10/10/20225
- Refactored main java with the help of claude since it was getting very cluttered, I believe this to be a good use of ai as these monotonous tasks can interfere with the flow of solving more complex problems.Spednign less time on trivial formatting allows me to focus on more pressing matters , progressing me as a developer.
- Comprehensive testing.
- Helper methods help readability


### Data structures used
- **Hashmap** for location storage
- **ArrayList** connection lists aswell as unit lists
- **Priority** We can add MANY locations without having to sacrifice performance

### OOP Principals Applied
- Encapsulation (private fields, public methods)
- Separation of concerns (NetworkManager vs EmergencyNetwork)
- Composition (objects containing objects)
- Single responsibility (each class has clear purpose)


### Algorithms
- Linear search (finding connections)
- Heap operations (priority queue)
- Distance Calculation(Euclidean approximation)
- Nearest neighbor(allocation)

### Challenges Overcome
- Nested data structures - Understanding Map<String, List<Connection>>
- Bidirectional updates - Remembering to update both directions
- Inner class imports - Importing EmergencyNetwork.Connection
- Priority queue ordering - Getting comparator logic right
- Integration - Connecting queue, allocator, and dispatcher



### What Phase 2 Demonstrates:
- System handles real-time changes (dynamic updates)
- Manages multiple concurrent incidents
- Uses appropriate data structures (PriorityQueue for sorting)
- Shows good software design (separation of concerns)
- All operations properly analyzed for complexity
- Code is organized and testable

### TODO
- Basic Reallocation - When units finish, make them available again for next incident
-pretty much the only thing left for this