ResponseUnit
├─ id (String)              → "UNIT001"
├─ type (String)            → "AMBULANCE"
└─ currentLocation (Location)
├─ id (String)       → "LOC001"
├─ name (String)     → "London"
├─ latitude (double)
└─ longitude (double)

Incident
├─ id (String)              → "INC001"
├─ description (String)     → "Fire"
└─ location (Location)
├─ id (String)       → "LOC002"
└─ name (String)     → "Manchester"

Connection (between locations)
├─ destinationId (String)   → "LOC002"
├─ distance (double)
└─ travelTime (double)