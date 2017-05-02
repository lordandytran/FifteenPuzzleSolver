public class FifteenPuzzle {

    private String state;
    private int cost;
    private int heuristic;
    private int index;

    public FifteenPuzzle(String initState) {
        state = initState;
        cost = 0;
        heuristic = genHeuristic();
        index = state.indexOf(" ");
    }

    public FifteenPuzzle(String newState, int prevCost) {
        state = newState;
        cost = prevCost + 1;
        heuristic = genHeuristic();
        index = state.indexOf(" ");
    }

    public String getState() {
        return state;
    }

    public int getHeuristic() {
        return heuristic;
    }

    public int getCost() {
        return cost;
    }

    public boolean isSolved() {
        return state.equals("123456789ABCDEF ") || state.equals("123456789ABCDFE ");
    }

    public String left() {
        if(index % 4 != 0) {
            char[] str = state.toCharArray();
            char temp = str[index];
            str[index] = str[index - 1];
            str[index - 1] = temp;
            return new String(str);
        }
        return null;
    }

    public String right() {
        if(index % 4 != 3) {
            char[] str = state.toCharArray();
            char temp = str[index];
            str[index] = str[index + 1];
            str[index + 1] = temp;
            return new String(str);
        }
        return null;
    }

    public String up() {
        if(index - 4 >= 0) {
            char[] str = state.toCharArray();
            char temp = str[index];
            str[index] = str[index - 4];
            str[index - 4] = temp;
            return new String(str);
        }
        return null;
    }

    public String down() {
        if(index + 4 < 16) {
            char[] str = state.toCharArray();
            char temp = str[index];
            str[index] = str[index + 4];
            str[index + 4] = temp;
            return new String(str);
        }
        return null;
    }

    private int genHeuristic() {
        int count = 0;
        int index = 0;
        int comp = 0;
        for(int i = 0; i < 16; i++) {
            index = i;
            String temp = String.valueOf(state.charAt(i));
            comp = "123456789ABCDEF ".indexOf(temp);
            if(index > comp) {
                while(index - 4 >= comp) {
                    index -= 4;
                    count++;
                }
                count += (index - comp);
            }
            if(index < comp) {
                while(index + 4 <= comp) {
                    index += 4;
                    count++;
                }
                count += (comp - index);
            }
        }
        return count; //For Greedy Best First Search
        //return cost + count; //For A* search
    }

    public String puzzleForm() {
        String str = "";
        if(state == null) {
            return str;
        }
        int space = 0;
        for(int i = 0; i < 16; i++) {
            System.out.print(state.charAt(i) + " ");
            if(space % 4 == 3) {
                System.out.println();
            }
            space++;
        }
        return str;
    }

    @Override
    public String toString() {
        return state;
    }
}
