{
	"patcher" : 	{
		"fileversion" : 1,
		"rect" : [ 904.0, 294.0, 755.0, 888.0 ],
		"bglocked" : 0,
		"defrect" : [ 904.0, 294.0, 755.0, 888.0 ],
		"openrect" : [ 0.0, 0.0, 0.0, 0.0 ],
		"openinpresentation" : 1,
		"default_fontsize" : 12.0,
		"default_fontface" : 0,
		"default_fontname" : "Arial",
		"gridonopen" : 0,
		"gridsize" : [ 4.0, 4.0 ],
		"gridsnaponopen" : 0,
		"toolbarvisible" : 1,
		"boxanimatetime" : 200,
		"imprint" : 0,
		"enablehscroll" : 1,
		"enablevscroll" : 1,
		"devicewidth" : 0.0,
		"boxes" : [ 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "print IN",
					"fontsize" : 12.0,
					"patching_rect" : [ 154.0, 46.0, 49.0, 20.0 ],
					"id" : "obj-8",
					"numinlets" : 1,
					"fontname" : "Arial",
					"numoutlets" : 0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "route monomes",
					"fontsize" : 12.0,
					"patching_rect" : [ 82.0, 94.0, 94.0, 20.0 ],
					"id" : "obj-9",
					"numinlets" : 1,
					"fontname" : "Arial",
					"numoutlets" : 2,
					"outlettype" : [ "", "" ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "outlet",
					"hint" : "Raw messages to SevenUp4LiveCore",
					"patching_rect" : [ 28.0, 742.0, 25.0, 25.0 ],
					"presentation" : 1,
					"id" : "obj-7",
					"numinlets" : 1,
					"numoutlets" : 0,
					"presentation_rect" : [ 75.0, 708.0, 25.0, 25.0 ],
					"comment" : ""
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "bpatcher",
					"args" : [  ],
					"patching_rect" : [ 65.0, 594.0, 340.0, 130.0 ],
					"presentation" : 1,
					"id" : "obj-6",
					"name" : "SevenUpMelodizer2SettingsPanel.maxpat",
					"numinlets" : 0,
					"numoutlets" : 0,
					"presentation_rect" : [ 61.0, 521.0, 305.0, 127.0 ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "bpatcher",
					"args" : [  ],
					"patching_rect" : [ 65.0, 458.0, 340.0, 130.0 ],
					"presentation" : 1,
					"id" : "obj-5",
					"name" : "SevenUpMelodizer1SettingsPanel.maxpat",
					"numinlets" : 0,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"presentation_rect" : [ 61.0, 384.0, 305.0, 127.0 ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "bpatcher",
					"args" : [  ],
					"patching_rect" : [ 65.0, 323.0, 340.0, 130.0 ],
					"presentation" : 1,
					"id" : "obj-2",
					"name" : "SevenUpLooperSettingsPanel.maxpat",
					"numinlets" : 0,
					"numoutlets" : 0,
					"presentation_rect" : [ 61.0, 245.0, 305.0, 127.0 ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "thispatcher",
					"fontsize" : 12.0,
					"patching_rect" : [ 195.0, 135.0, 69.0, 20.0 ],
					"id" : "obj-4",
					"numinlets" : 1,
					"fontname" : "Arial",
					"numoutlets" : 2,
					"outlettype" : [ "", "" ],
					"save" : [ "#N", "thispatcher", ";", "#Q", "end", ";" ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "inlet",
					"patching_rect" : [ 63.0, 17.0, 25.0, 25.0 ],
					"id" : "obj-3",
					"numinlets" : 0,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"comment" : ""
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "bpatcher",
					"args" : [  ],
					"patching_rect" : [ 65.0, 185.0, 340.0, 130.0 ],
					"presentation" : 1,
					"id" : "obj-1",
					"name" : "SevenUpConnectionSettingsPanel.maxpat",
					"numinlets" : 0,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"presentation_rect" : [ 59.0, 106.0, 307.0, 127.0 ]
				}

			}
 ],
		"lines" : [ 			{
				"patchline" : 				{
					"source" : [ "obj-3", 0 ],
					"destination" : [ "obj-8", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-3", 0 ],
					"destination" : [ "obj-9", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-9", 1 ],
					"destination" : [ "obj-4", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-1", 0 ],
					"destination" : [ "obj-7", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
 ],
		"parameters" : 		{
			"obj-6::obj-5" : [ "live.gain~[1]", "live.gain~", 0 ],
			"obj-5::obj-11" : [ "live.menu[18]", "live.menu", 0 ],
			"obj-5::obj-6" : [ "live.text", "live.menu", 0 ],
			"obj-5::obj-5" : [ "live.menu[14]", "live.menu", 0 ],
			"obj-5::obj-40" : [ "live.menu[17]", "live.menu", 0 ],
			"obj-5::obj-37" : [ "live.menu[12]", "live.menu", 0 ],
			"obj-5::obj-32" : [ "live.menu[15]", "live.menu", 0 ],
			"obj-5::obj-2" : [ "live.menu[2]", "live.menu", 0 ],
			"obj-5::obj-33" : [ "live.menu[19]", "live.menu", 0 ],
			"obj-2::obj-1" : [ "live.menu[10]", "live.menu", 0 ],
			"obj-5::obj-36" : [ "live.menu[13]", "live.menu", 0 ],
			"obj-1::obj-4" : [ "live.toggle", "live.toggle", 0 ],
			"obj-6::obj-6" : [ "live.gain~[2]", "live.gain~", 0 ],
			"obj-1::obj-15" : [ "live.menu[11]", "live.menu", 0 ],
			"obj-1::obj-14" : [ "live.numbox[1]", "live.numbox", 0 ],
			"obj-5::obj-9" : [ "live.menu[1]", "live.menu", 0 ],
			"obj-6::obj-4" : [ "live.gain~", "live.gain~", 0 ],
			"obj-1::obj-13" : [ "live.numbox", "live.numbox", 0 ],
			"obj-5::obj-8" : [ "live.menu[16]", "live.menu", 0 ]
		}

	}

}
