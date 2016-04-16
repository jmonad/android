package com.monadroid.sampleapp;

import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.jmonad.seq.Seq;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_home)
public class HomeActivity extends AppCompatActivity {

  private static final int testSamples = 1000000;

  @ViewById
  TextView contentText;

  @UiThread
  protected void printText(String text) {
    contentText.append(text + "\n");
  }

  @AfterViews
  protected void afterViews() {
    test0();
    test1();
  }

  @Background(serial = "Tests")
  protected void test0() {
    printText("Starting Test 0 - Map Function");
    Integer[] integers = new Integer[testSamples];

    printText("   - Filling " + testSamples + " integers to test.");
    for (int i = 0; i < testSamples; i++) {
      integers[i] = i;
    }

    long startTime = System.currentTimeMillis();
    long totalTime = 0;
    long endTime;

    // Initialization Test
    printText("   - Starting Test 0");
    Seq<Integer> values = new Seq<>(integers);
    endTime = System.currentTimeMillis();
    totalTime += (endTime - startTime);
    printText("   - Created Seq in " + (endTime - startTime) + " ms");

    // Map Test
    printText("   - Mapping x -> x * 2");
    startTime = System.currentTimeMillis();
    values = values.map(x -> x * 2);
    endTime = System.currentTimeMillis();
    totalTime += (endTime - startTime);
    printText("   - Mapped x -> x * 2 in " + (endTime - startTime) + " ms");

    printText("   - Total operating time: " + totalTime + " ms for Test 0");
    printText("\n");
  }

  @Background(serial = "Tests")
  protected void test1() {
    printText("Starting Test 1 - Filter Function");
    Integer[] integers = new Integer[testSamples];

    printText("   - Filling " + testSamples + " integers to test.");
    for (int i = 0; i < testSamples; i++) {
      integers[i] = i % 125;
    }

    long startTime = System.currentTimeMillis();
    long totalTime = 0;
    long endTime;

    // Initialization Test
    printText("   - Starting Test 1");
    Seq<Integer> values = new Seq<>(integers);
    endTime = System.currentTimeMillis();
    totalTime += (endTime - startTime);
    printText("   - Created Seq in " + (endTime - startTime) + " ms");

    // Filter Test
    printText("   - Filtering x > 10 && x < 40");
    startTime = System.currentTimeMillis();
    values = values.filter(x -> x > 10 && x < 40);
    endTime = System.currentTimeMillis();
    totalTime += (endTime - startTime);
    printText("   - Filtered x > 10 && x < 40 in " + (endTime - startTime) + " ms");

    printText("   - Total operating time: " + totalTime + " ms for Test 1");
    printText("\n");
  }

}
