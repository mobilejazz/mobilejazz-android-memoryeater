package cat.mobilejazz.utilities.memory_eater;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MemoryEater extends Activity {

	private List<byte[]> memory;
	private TextView label;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.memory_eater);
		label = (TextView) findViewById(R.id.label);
		memory = new ArrayList<byte[]>();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.memory_eater, menu);
		return true;
	}

	private int getBytes() {
		int sum = 0;
		for (byte[] b : memory) {
			sum += b.length;
		}
		return sum;
	}

	private void allocate(int numBytes) {
		memory.add(new byte[numBytes]);
		label.setText(String.format("%.1f MB allocated", (float) getBytes() / 1024f / 1024f));
	}

	public void allocate512(View view) {
		allocate(512 * 1024);
	}

	public void allocate1024(View view) {
		allocate(1024 * 1024);
	}

	public void allocate2048(View view) {
		allocate(2048 * 1024);
	}

	public void allocate4096(View view) {
		allocate(4096 * 1024);
	}

	public void clear(View view) {
		memory.clear();
		System.gc();
		label.setText(String.format("%.1f MB allocated", (float) getBytes() / 1024f / 1024f));
	}

}
