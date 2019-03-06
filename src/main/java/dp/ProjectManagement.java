package dp;

import java.util.*;

/**
 * @author rpurigella TODO
 */
public class ProjectManagement {

    static class Project {
        int start;
        int end;
        int value;
        Project prev;

        public Project(int start, int end, int value) {
            this.start = start;
            this.end = end;
            this.value = value;
        }

        static Comparator<Project> EndTimeComparator = new Comparator<Project>() {
            @Override
            public int compare(Project o1, Project o2) {
                return o1.end - o2.end;
            }
        };
    }

    public static void main(String[] args) {
        Project p;
        List<Project> list = new ArrayList<>();
        p = new Project(1, 3, 4);
        list.add(p);
        p = new Project(2, 4, 3);
        list.add(p);
        p = new Project(5, 7, 2);
        list.add(p);
        p = new Project(6, 8, 3);
        list.add(p);

        int result = maxValue(list);
    }

    static int maxValue(List<Project> pList) {
        Collections.sort(pList, Project.EndTimeComparator);
        return 0;
    }
}

