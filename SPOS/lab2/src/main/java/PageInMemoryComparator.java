import java.util.Comparator;

public class PageInMemoryComparator implements Comparator<Page> {
    @Override
    public int compare(Page o1, Page o2) {
        if (o1.inMemTime == o2.inMemTime) {
            return 0;
        } else if (o1.inMemTime > o2.inMemTime) {
            return 1;
        } else {
            return -1;
        }
    }
}
