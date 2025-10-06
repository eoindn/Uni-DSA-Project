# Phase 1: Basic Implementation (40-49)

## Goal
Create a basic emergency network with simple incident registration and nearest-unit allocation.

## Date Started: [06/10/2025]

## Tasks
- [ done ] Create core domain models
- [ done ] Build basic graph structure
- [ done ] Implement nearest-unit allocation
- [ done ] (in the main file of the core package) ] Write basic tests

## Design Decisions

- 06/10/20225
- Getters and setters for appropriate fields, 
- setters are only set for things that would be dynamic such as the availability of vehicles and the location of an ambulance for example.


### Why HashMap for the locations?
- **Fast O(1) look up** to find a location via its ID
- **Efficient Storage** Only stores necessary information
- **Its scalable** We can add MANY locations without having to sacrifice performance

### Why Adjacency List for Graph?
- **Space efficiency:** O(V + E) where V = vertices (locations), E = edges (connections)
- **Easy to traverse:** Can quickly find all neighbors of a location
- **Dynamic:** Easy to add/remove connections

### Why Bidirectional Connections?
- Emergency vehicles need to travel both ways on roads
- When we connect London ↔ Manchester, both cities need to know about each other
- Makes route-finding algorithms work correctly later

---





### Network Structure (Graph)
- ✅ **EmergencyNetwork.java** - Main graph structure
    - Uses `Map<String, Location>` to store locations (like a dictionary)
    - Uses `Map<String, List<String>>` for connections (adjacency list)
    - Bidirectional connections between locations

### Data Structures Used
- Location: Represents cities/dispatch centers
- Incident: Represents emergencies
- ResponseUnit: Represents ambulances/fire trucks

## Challenges Encountered


### 1. Constructor Parameter Order Bug
**Problem:** Location constructor had (name, id) but I called it with (id, name)  
**Solution:** Changed constructor to (id, name) - standard convention is ID first  


### 2. NullPointerException When Retrieving
**Problem:** `retrieved` was null because I used wrong ID  
**Solution:** Made sure to use correct location IDs that actually exist in the map  
**Lesson:** Debug step-by-step, check what's actually stored

### 3. Understanding Graph Connections
**Problem:** Felt abstract - didn't understand what "connecting" meant  
**Solution:** Thought of it as building a road network for emergency vehicles  
**Lesson:** Making abstract concepts concrete helps understanding



---




## Java skills refreshed
- The importance of parameter orders
- toString() for debugging
- Generic types: `Map<String, List<String>>`



## Complexity Analysis
- O(1) look up for locations via location ID
- O(V + E) for adjacency lists where V = vertices(locations) and E = edges(locations)
