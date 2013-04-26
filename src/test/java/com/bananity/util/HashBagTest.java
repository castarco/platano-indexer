package com.bananity.util;

// Main Class
import com.bananity.util.HashBag;

// Java Utils
import java.util.Arrays;

// Junit
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


/**
 * @author Andreu Correa Casablanca
 */
@RunWith(JUnit4.class)
public class HashBagTest
{
	@Test
	public void test_equals () {
		// Empty HashBag
		HashBag<String> aHB = new HashBag<String>();
		Assert.assertEquals(0, aHB.size());
		Assert.assertEquals(0, aHB.uniqueItemsCount());

		// HashBag from an array
		HashBag<String> bHB = new HashBag<String>(new String[] {"a", "b", "c"});

		// HashBag from a Collection
		HashBag<String> cHB = new HashBag<String>(Arrays.asList(new String[] {"a", "b", "c"}));
		
		// HashBag from a HashBag
		HashBag<String> dHB = new HashBag<String>(bHB);

		// HashBag from a Bag
		HashBag<String> eHB = new HashBag<String>((IBag)bHB);

		// 
		HashBag<String> fHB = new HashBag<String>(new String[] {"a", "b", "c", "d"});
		HashBag<String> gHB = new HashBag<String>(new String[] {"a", "b", "c", "c"});


		// Checking equality (and it's symmetry)
		Assert.assertEquals(bHB, cHB);
		Assert.assertEquals(cHB, bHB);

		Assert.assertEquals(cHB, dHB);
		Assert.assertEquals(dHB, cHB);

		Assert.assertEquals(dHB, eHB);
		Assert.assertEquals(eHB, dHB);

		Assert.assertEquals(eHB, bHB);
		Assert.assertEquals(bHB, eHB);

		// Checking equality (just it's reflexivity)
		Assert.assertEquals(aHB, aHB);
		Assert.assertEquals(bHB, bHB);
		Assert.assertEquals(cHB, cHB);
		Assert.assertEquals(dHB, dHB);
		Assert.assertEquals(eHB, eHB);

		// Checking inequality
		Assert.assertNotEquals(aHB, bHB);
		Assert.assertNotEquals(aHB, cHB);
		Assert.assertNotEquals(aHB, dHB);
		Assert.assertNotEquals(aHB, eHB);

		Assert.assertNotEquals(bHB, fHB);
		Assert.assertNotEquals(bHB, gHB);
		Assert.assertNotEquals(fHB, gHB);

		// Checking inequality between different classes
		Assert.assertNotEquals(aHB, new Integer(1));
	}

	@Test
	public void test_add () {
		HashBag<String> aHB = new HashBag<String>();
		Assert.assertFalse(aHB.contains("Bananity"));
		Assert.assertFalse(aHB.contains("Pinterest"));

		aHB.add("Bananity", 3);
		Assert.assertTrue(aHB.contains("Bananity"));
		Assert.assertEquals(3, aHB.getTimes("Bananity"));
		Assert.assertEquals(3, aHB.size());
		Assert.assertEquals(1, aHB.uniqueItemsCount());

		aHB.add("Pinterest", 5);
		Assert.assertTrue(aHB.contains("Pinterest"));
		Assert.assertEquals(5, aHB.getTimes("Pinterest"));
		Assert.assertEquals(8, aHB.size());
		Assert.assertEquals(2, aHB.uniqueItemsCount());

		aHB.add("Bananity", 1);
		Assert.assertEquals(4, aHB.getTimes("Bananity"));
		Assert.assertEquals(9, aHB.size());
		Assert.assertEquals(2, aHB.uniqueItemsCount());

		aHB.add("Pinterest", 0);
		Assert.assertEquals(5, aHB.getTimes("Pinterest"));
		Assert.assertEquals(9, aHB.size());
		Assert.assertEquals(2, aHB.uniqueItemsCount());
	}

	@Test
	public void test_decreaseValue () {
		HashBag<String> aHB = new HashBag<String>(new String[] {
			"Bty", "Bty", "Love", "Love", "Love", "Hate"
		});

		Assert.assertEquals(3, aHB.decreaseValue("Love"));
		Assert.assertEquals(5, aHB.size());
		Assert.assertEquals(2, aHB.getTimes("Bty"));
		Assert.assertEquals(2, aHB.getTimes("Love"));
		Assert.assertEquals(1, aHB.getTimes("Hate"));
		Assert.assertEquals(3, aHB.uniqueItemsCount());

		Assert.assertEquals(1, aHB.decreaseValue("Hate"));
		Assert.assertEquals(4, aHB.size());
		Assert.assertEquals(2, aHB.getTimes("Bty"));
		Assert.assertEquals(2, aHB.getTimes("Love"));
		Assert.assertEquals(0, aHB.getTimes("Hate"));
		Assert.assertEquals(2, aHB.uniqueItemsCount());
	}

	@Test
	public void test_removeValue () {
		HashBag<String> aHB = new HashBag<String>(new String[] {
			"Bty", "Bty", "Love", "Love", "Love", "Hate"
		});

		Assert.assertEquals(3, aHB.removeValue("Love"));
		Assert.assertEquals(3, aHB.size());
		Assert.assertEquals(2, aHB.getTimes("Bty"));
		Assert.assertEquals(0, aHB.getTimes("Love"));
		Assert.assertEquals(1, aHB.getTimes("Hate"));
		Assert.assertEquals(2, aHB.uniqueItemsCount());

		Assert.assertEquals(2, aHB.removeValue("Bty"));
		Assert.assertEquals(1, aHB.size());
		Assert.assertEquals(0, aHB.getTimes("Bty"));
		Assert.assertEquals(0, aHB.getTimes("Love"));
		Assert.assertEquals(1, aHB.getTimes("Hate"));
		Assert.assertEquals(1, aHB.uniqueItemsCount());

		Assert.assertEquals(1, aHB.removeValue("Hate"));
		Assert.assertEquals(0, aHB.size());
		Assert.assertEquals(0, aHB.getTimes("Bty"));
		Assert.assertEquals(0, aHB.getTimes("Love"));
		Assert.assertEquals(0, aHB.getTimes("Hate"));
		Assert.assertEquals(0, aHB.uniqueItemsCount());

		Assert.assertEquals(new HashBag<String>(), aHB);
	}

	@Test
	public void test_union () {
		HashBag<String> aHB = new HashBag<String>(new String[] {
			"Bty", "Bty", "Love", "Love", "Love", "Hate"
		});

		HashBag<String> bHB = new HashBag<String>(new String[] {
			"Bty", "Love", "Love", "Hate", "Hate", "Hate"
		});

		HashBag<String> abHB = new HashBag<String>(new String[] {
			"Bty", "Bty", "Love", "Love", "Love", "Hate", "Hate", "Hate"
		});

		Assert.assertEquals(aHB, aHB.union(aHB));
		Assert.assertEquals(abHB, aHB.union(bHB));
		Assert.assertEquals(abHB, bHB.union(aHB));
	}

	@Test
	public void test_intersection () {
		HashBag aHB = new HashBag<String>(new String[] {
			"Bty", "Bty", "Love", "Love", "Love", "Hate"
		});

		HashBag<String> bHB = new HashBag<String>(new String[] {
			"Bty", "Love", "Love", "Hate", "Hate", "Hate"
		});

		HashBag<String> abHB = new HashBag<String>(new String[] {
			"Bty", "Love", "Love", "Hate"
		});

		Assert.assertEquals(aHB, aHB.intersection(aHB));
		Assert.assertEquals(abHB, aHB.intersection(bHB));
		Assert.assertEquals(abHB, bHB.intersection(aHB));
	}

	@Test
	public void test_difference () {
		HashBag aHB = new HashBag<String>(new String[] {
			"Bty", "Bty", "Love", "Love", "Love", "Hate"
		});

		HashBag bHB = new HashBag<String>(new String[] {
			"Love", "Love", "Love"
		});

		HashBag<String> abHB = new HashBag<String>(new String[] {
			"Bty", "Bty", "Hate"
		});

		Assert.assertEquals(new HashBag<String>(), aHB.difference(aHB));
		Assert.assertEquals(abHB, aHB.difference(bHB));
		Assert.assertEquals(new HashBag<String>(), bHB.difference(aHB));
	}
}