package mtn.sevenuplive.scales;

public class Scale {
	
	public int Degrees[];
	public ScaleName Name;
	public String label;
	
	public Scale(ScaleName name){
		Degrees = new int[7];
		switch(name){
		case MinorPentatonic:
			Name = ScaleName.MinorPentatonic;
			label = "Pentatonic (Minor)";
			Degrees[0] = 0;
			Degrees[1] = 3;
			Degrees[2] = 4;
			Degrees[3] = 5;
			Degrees[4] = 7;
			Degrees[5] = 12;
			Degrees[6] = 15;
			break;
		case Pentatonic:
			Name = ScaleName.Pentatonic;
			label = "Pentatonic";
			Degrees[0] = 0;
			Degrees[1] = 2;
			Degrees[2] = 4;
			Degrees[3] = 7;
			Degrees[4] = 9;
			Degrees[5] = 12;
			Degrees[6] = 14;
			break;
		case Blues:
			Name = ScaleName.Blues;
			label = "Blues";
			Degrees[0] = 0;
			Degrees[1] = 2;
			Degrees[2] = 3;
			Degrees[3] = 4;
			Degrees[4] = 7;
			Degrees[5] = 9;
			Degrees[6] = 12;
			break;
		case MinorBlues:
			Name = ScaleName.MinorBlues;
			label = "Blues (Minor)";
			Degrees[0] = 0;
			Degrees[1] = 3;
			Degrees[2] = 5;
			Degrees[3] = 6;
			Degrees[4] = 7;
			Degrees[5] = 10;
			Degrees[6] = 12;
			break;
		case Dorian:
			Name = ScaleName.Dorian;
			label = "Dorian";
			Degrees[0] = 0;
			Degrees[1] = 2;
			Degrees[2] = 3;
			Degrees[3] = 5;
			Degrees[4] = 7;
			Degrees[5] = 9;
			Degrees[6] = 10;
			break;
		case UltraLocrian:
			Name = ScaleName.UltraLocrian;
			label = "Ultra Locrian";
			Degrees[0] = 0;
			Degrees[1] = 1;
			Degrees[2] = 3;
			Degrees[3] = 4;
			Degrees[4] = 6;
			Degrees[5] = 8;
			Degrees[6] = 9;
			break;
		case Major:
			Name = ScaleName.Major;
			label = "Major";
			Degrees[0] = 0;
			Degrees[1] = 2;
			Degrees[2] = 4;
			Degrees[3] = 5;
			Degrees[4] = 7;
			Degrees[5] = 9;
			Degrees[6] = 11;
			break;
		case MinorSeven:
			Name = ScaleName.MinorSeven;
			label = "Minor Seven";
			Degrees[0] = 0;
			Degrees[1] = 2;
			Degrees[2] = 3;
			Degrees[3] = 5;
			Degrees[4] = 7;
			Degrees[5] = 9;
			Degrees[6] = 10;
			break;
		case Billian:
			Name = ScaleName.Billian;
			label = "Billian";
			Degrees[0] = 0;
			Degrees[1] = 2;
			Degrees[2] = 3;
			Degrees[3] = 5;
			Degrees[4] = 7;
			Degrees[5] = 8;
			Degrees[6] = 10;
			break;
		case Minor:
			Name = ScaleName.Minor;
			label = "Minor";
			Degrees[0] = 0;
			Degrees[1] = 2;
			Degrees[2] = 3;
			Degrees[3] = 5;
			Degrees[4] = 7;
			Degrees[5] = 9;
			Degrees[6] = 11;
			break;
		case Mullnixian:
			Name = ScaleName.Mullnixian;
			label = "Mullnixian";
			Degrees[0] = 0;
			Degrees[1] = 1;
			Degrees[2] = 3;
			Degrees[3] = 5;
			Degrees[4] = 7;
			Degrees[5] = 8;
			Degrees[6] = 10;
			break;
		case Chromatic:
			Name = ScaleName.Chromatic;
			label = "Chromatic";
			Degrees = new int[12];
			Degrees[0] = 0;
			Degrees[1] = 1;
			Degrees[2] = 2;
			Degrees[3] = 3;
			Degrees[4] = 4;
			Degrees[5] = 5;
			Degrees[6] = 6;
			Degrees[7] = 7;
			Degrees[8] = 8;
			Degrees[9] = 9;
			Degrees[10] = 10;
			Degrees[11] = 11;
			break;
		}
	}
	
	

}
