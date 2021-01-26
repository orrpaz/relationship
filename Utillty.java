import java.util.*;

public class Utillty {
    HashMap<Person, Set<Person>> map;
    Person[] person;

    public Utillty(Person[] person) {
        this.person = person;
        init(this.person);
    }

    public void init(Person[] person) {
        map = new HashMap<Person, Set<Person>>();
        for (int i = 0; i < person.length; i++) {
            for (int j = 0; j < person.length; j++) {
                // same person, ignored.
                if (i == j) {
                    continue;
                }
                if (!map.containsKey(person[j])) {
                    map.put(person[j], new HashSet<>());
                }
                if (person[j].isDirectConnection(person[i])) {
                    map.get(person[j]).add(person[i]);
                    map.get(person[i]).add(person[j]);
                }
            }
        }
    }

    public int FindMinRelationLevel(Person personA, Person personB) {
        HashMap<Person, Person> prev = new HashMap<Person, Person>();
        return findShortest(personA, personB, prev);

    }

    private int findShortest(Person personA, Person personB, HashMap<Person, Person> prev) {
        int distance = 0;
        LinkedList<Person> queue = new LinkedList<>();
        Set<Person> visited = new HashSet<>();

        Person curr = personA;
        visited.add(curr);
        queue.add(curr);
        while (!queue.isEmpty()) {
            curr = queue.remove();
            // we arrive to person b
            if (curr.equals(personB)) {
                // first time show personB
                break;
            } else {
                for (Person adj : map.get(curr)) {
                    if (visited.contains(adj)) {
                        continue;
                    }
                    queue.add(adj);
                    // to prevent circular
                    visited.add(adj);
                    prev.put(adj, curr);


                }
            }
        }
        // no path from personA to personB
        if (!curr.equals(personB)) {
            return -1;
        }
        // restore the path from personB to personA and inc distance
        Person p = personB;
        while(p != null){
            distance++;
            p = prev.get(p);
        }

        return distance;
    }
}

