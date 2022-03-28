
package commons;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActivityTest {

    @Test
    void constructorTest() {
        Activity activity1 = new Activity("Activity1","Activity1.png","Path1",224242);
        assertNotNull(activity1);
    }

    @Test
    void getImgPath() {
        Activity activity1 = new Activity("Activity1","Activity1.png","Path1",224242);
        assertEquals("Activity1.png",activity1.getImgPath());
    }

    @Test
    void setImgPath() {
        Activity activity1 = new Activity("Activity1","Activity1.png","Path1",224242);
        activity1.setImgPath("activity2.png");
        assertEquals("activity2.png",activity1.getImgPath());
    }


    @Test
    void getConsumption() {
        Activity activity1 = new Activity("Activity1","Activity1.png","Path1",224242);
        assertEquals(224242,activity1.getConsumption());
    }

    @Test
    void setConsumption() {
        Activity activity1 = new Activity("Activity1","Activity1.png","Path1",224242);
        activity1.setConsumption(22);
        assertEquals(22,activity1.getConsumption());
    }

   @Test
   void getTitle(){
       Activity activity1 = new Activity("Activity1","Activity1.png","Path1",224242);
       assertEquals("Path1",activity1.getTitle());
   }

    @Test
    void setTitle(){
        Activity activity1 = new Activity("Activity1","Activity1.png","Path1",224242);
        activity1.setTitle("title");
        assertEquals("title",activity1.getTitle());
    }

    @Test
    void getActivityName() {
        Activity activity1 = new Activity("Activity1","Activity1.png","Path1",224242);
        assertEquals("Activity1",activity1.getActivityName());
    }

    @Test
    void setActivityName() {
        Activity activity1 = new Activity("Activity1","Activity1.png","Path1",224242);
        activity1.setActivityName("Mamaie");
        assertEquals(activity1.getActivityName(),"Mamaie");
    }

    @Test
    void hashCodeTest() {
        Activity activity1 = new Activity("Activity1","Activity1.png","Path1",224242);
        Activity activity2 = new Activity("Activity1","Activity1.png","Path1",224242);
        assertEquals(activity1.hashCode(), activity2.hashCode());
    }

    @Test
    void nothashCodeTest() {
        Activity activity1 = new Activity("Activity1","Activity1.png","Path1",224242);
        Activity activity2 = new Activity("Activity2","Activity2.png","Path2",2242422);
        assertNotEquals(activity1.hashCode(), activity2.hashCode());
    }

    @Test
    void testEquals() {
        Activity activity1 = new Activity("Activity1","Activity1.png","Path1",224242);
        Activity activity2 = new Activity("Activity1","Activity1.png","Path1",224242);
        assertTrue(activity1.equals(activity2));
    }

    @Test
    void testNotEquals() {
        Activity activity1 = new Activity("Activity1","Activity1.png","Path",224242);
        Activity activity2 = new Activity("Activity1","Activity1.png","Path1",224242);
        assertFalse(activity1.equals(activity2));
    }




}