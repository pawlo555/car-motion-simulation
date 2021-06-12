import java.util.ArrayList;


public class intersectionGenerator {
    ArrayList<Crossing> listOfCrossings = new ArrayList<>();
    public int xMin;
    public int yMin;
    public int xMax;
    public int yMax;


    public intersectionGenerator(String data,int xMin,int yMin,int xMax,int yMax){
        this.xMin = xMin;
        this.yMin = yMin;
        this.xMax = xMax;
        this.yMax = yMax;

        for(int i=0;i< data.length();i+=1){
            String crossing = new String();
            int a = i;
            while(data.charAt(a) != '$'){
                crossing = crossing + data.charAt(a);
            }
            crossing = crossing + data.charAt(a);
            listOfCrossings.add(generate(crossing));
            i = a;
        }
    }
    public Crossing generate(String data){
        ArrayList<Road> roads = new ArrayList<>();
        int number = 0;
        int a = 0;
        int max = data.length();
        int index = 0;
        String name = null;
        ArrayList<Integer> x = null;
        ArrayList<Integer> y = null;
        ArrayList<ArrayList<Integer>> e = null;
        while(a != max && data.charAt(a) != '$'){
            String line = new String();
            while(data.charAt(a) != '\n'){
                line = line + data.charAt(a);
                a+=1;
            }
            a+=1;
            switch(line.charAt(0)){
                case '#':
                    name = readCrossing(line);
                    break;
                case '-':
                    if(line.charAt(1) == 'x'){
                        x = (readX(line));
                    } else if(line.charAt(1) == 'y') {
                        y = (readY(line));
                    }
                case '*':
                    e.add(readE(line));
                    break;
                case '%':
                    if(x.size() != 0){
                        roads.add(new Road(x,y,e,index));
                        x = null;
                        y = null;
                        e = null;
                        index = readRoad(line);
                    } else {
                        index = readRoad(line);
                    }
                    break;
            }

        }
        return new Crossing(roads,this.xMax,this.xMin,this.yMax,this.yMin,number);
    }

    public String readCrossing(String line){
        int len = line.length();
        String name = new String();
        for(int i=10;i<len;i+=1){
            name = name + line.charAt(i);
        }
        return name;
    }

    public static int readRoad(String line){
        int len = line.length();
        String index = new String();
        for(int i=6;i<len;i+=1){
            index = index + line.charAt(i);
        }
        return Integer.parseInt(index);
    }

    public static ArrayList<Integer> readX(String line){
        return null;
    }

    public static ArrayList<Integer> readY(String line){
        return null;
    }

    public static ArrayList<Integer> readE(String Line){
        return null;
    }

}
