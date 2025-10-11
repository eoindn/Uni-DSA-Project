# Phase 3: Comprehensive Implementation (60-69)

## Goal
- Fully functional dispatch with real-time updates
- Efficient allocation algorithm (multi-criteria)
- Initial predictive logic
- Resource management
- Detailed documentation with complexity analysis

## Date Started: [11/10/2025]

## Tasks
Right now the BasicAllocator only considers distance.
Upgrades to consider:

- Distance (how far)
- Unit type (ambulance for medical, fire truck for fires)
- Severity (critical incidents get priority)
-  Travel time (traffic conditions)


## Features implemented
### 1. Multi-Criteria Allocation (OptimisedAllocator) 🔄

**What it does:**
- Scores units based on 4 factors instead of just distance
- Uses weighted scoring system to find best match

**Scoring Components:**
1. **Distance Score (40% weight)** - How far the unit is
2. **Type Match Score (30% weight)** - Is it the right vehicle type?
3. **Severity Score (20% weight)** - How urgent is the incident?
4. **Travel Time Score (10% weight)** - How long to get there?


**Complexity:** O(n) where n = number of units

---

## Design Decisions

### Why Weighted Scoring?
**Problem:** BasicAllocator only considered distance - not realistic  
**Solution:** Multi-criteria decision making  
**Benefit:** More intelligent dispatch decisions

### Weight Distribution Rationale:
- **Distance (40%)** - Still most important for fast response
- **Type Match (30%)** - Critical to send right vehicle
- **Severity (20%)** - Urgent incidents prioritised
- **Travel Time (10%)** - Considers traffic/road conditions

### Type Matching Logic:
- Keyword detection in incident description
- Perfect match = 0 (best), Wrong type = 50 (penalty), Unknown = 25 (neutral)
- Keywords: FIRE, SMOKE, MEDICAL, HEART, SHOT, CRIME, etc.

---

## Challenges Encountered

### Challenge: Understanding Multi-Criteria Algorithms
**Problem:** Never implemented weighted scoring before  
**Solution:** Broke it down into individual scoring functions first  
**Learning:** Complex algorithms become manageable when decomposed

### Challenge: Debugging Complex Logic
**Problem:** Hard to track what's happening across multiple methods  
**Solution:** Added print statements to trace scoring process  
**Learning:** Debugging in Java requires systematic approach

### Challenge: Balancing Weights
**Problem:** Fire truck dispatched to gunshot victim (proximity won over type)  
**Solution:** Can adjust weights to prioritise type matching more  
**Learning:** Algorithm tuning is iterative - no "perfect" weights

---

## Next Steps

### To Complete Phase 3 (60-69%):
- [ ] Test with more varied scenarios
- [ ] Fine-tune weights based on results
- [ ] Add more incident type keywords
- [ ] Implement pathfinding (Dijkstra's algorithm)
- [ ] Add predictive logic (incident hotspot tracking)
- [ ] Create detailed class diagrams
- [ ] Complete complexity analysis for all operations

---

## Technical Notes

### Scoring Normalisation:
All scores normalised to 0-100 scale for fair comparison:
- Distance: `distance / 5.0` (assumes max ~500km)
- Time: `time / 3.0` (assumes max ~300 min)
- Type: Binary (0, 25, or 50)
- Severity: Inverted priority `100 - (priority × 25)`

### Why Lower Score = Better?
Makes math simpler - want to minimise total score  
Alternative would be maximising, but requires inverting some scores

---

## Reflection

### What Went Well:
- Successfully implemented weighted algorithm
- All 4 scoring functions working correctly
- Understanding of multi-criteria decision-making improved

### What Was Challenging:
- Keeping track of all the methods and their purposes
- Understanding which objects have which methods
- Debugging without clear visibility into scoring process
- Found initial algorithm confusing but it made more sense after understanding how the weights work then it was a case of simple maths.

### Improvements for Tomorrow:
- Add comprehensive debug output
- Create quick reference for class methods
- Test with realistic scenarios
