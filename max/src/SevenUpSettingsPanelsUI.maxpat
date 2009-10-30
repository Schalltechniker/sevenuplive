{
	"patcher" : 	{
		"fileversion" : 1,
		"rect" : [ 1114.0, 483.0, 610.0, 946.0 ],
		"bglocked" : 0,
		"defrect" : [ 1114.0, 483.0, 610.0, 946.0 ],
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
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 154.0, 46.0, 49.0, 20.0 ],
					"id" : "obj-8",
					"fontname" : "Arial",
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "route monomes",
					"numinlets" : 1,
					"numoutlets" : 2,
					"patching_rect" : [ 82.0, 94.0, 94.0, 20.0 ],
					"id" : "obj-9",
					"fontname" : "Arial",
					"outlettype" : [ "", "" ],
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "outlet",
					"hint" : "Raw messages to SevenUp4LiveCore",
					"presentation_rect" : [ 75.0, 708.0, 25.0, 25.0 ],
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 28.0, 742.0, 25.0, 25.0 ],
					"presentation" : 1,
					"id" : "obj-7",
					"comment" : ""
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "bpatcher",
					"presentation_rect" : [ 61.0, 521.0, 290.0, 127.0 ],
					"numinlets" : 0,
					"args" : [  ],
					"numoutlets" : 0,
					"patching_rect" : [ 67.0, 597.0, 238.0, 127.0 ],
					"presentation" : 1,
					"id" : "obj-6",
					"name" : "SevenUpMelodizer2SettingsPanel.maxpat"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "bpatcher",
					"presentation_rect" : [ 61.0, 383.0, 291.0, 127.0 ],
					"numinlets" : 0,
					"args" : [  ],
					"numoutlets" : 0,
					"patching_rect" : [ 66.0, 459.0, 238.0, 127.0 ],
					"presentation" : 1,
					"id" : "obj-5",
					"name" : "SevenUpMelodizer1SettingsPanel.maxpat"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "bpatcher",
					"presentation_rect" : [ 61.0, 245.0, 291.0, 127.0 ],
					"numinlets" : 0,
					"args" : [  ],
					"numoutlets" : 0,
					"patching_rect" : [ 66.0, 323.0, 238.0, 127.0 ],
					"presentation" : 1,
					"id" : "obj-2",
					"name" : "SevenUpLooperSettingsPanel.maxpat"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "thispatcher",
					"numinlets" : 1,
					"numoutlets" : 2,
					"patching_rect" : [ 195.0, 135.0, 69.0, 20.0 ],
					"id" : "obj-4",
					"fontname" : "Arial",
					"outlettype" : [ "", "" ],
					"fontsize" : 12.0,
					"save" : [ "#N", "thispatcher", ";", "#Q", "end", ";" ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "inlet",
					"numinlets" : 0,
					"numoutlets" : 1,
					"patching_rect" : [ 63.0, 17.0, 25.0, 25.0 ],
					"id" : "obj-3",
					"outlettype" : [ "" ],
					"comment" : ""
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "bpatcher",
					"presentation_rect" : [ 59.0, 106.0, 293.0, 127.0 ],
					"numinlets" : 0,
					"args" : [  ],
					"numoutlets" : 1,
					"patching_rect" : [ 65.0, 185.0, 238.0, 127.0 ],
					"presentation" : 1,
					"id" : "obj-1",
					"outlettype" : [ "" ],
					"name" : "SevenUpConnectionSettingsPanel.maxpat"
				}

			}
 ],
		"lines" : [ 			{
				"patchline" : 				{
					"source" : [ "obj-1", 0 ],
					"destination" : [ "obj-7", 0 ],
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
					"source" : [ "obj-3", 0 ],
					"destination" : [ "obj-9", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-3", 0 ],
					"destination" : [ "obj-8", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
 ],
		"parameters" : 		{
			"obj-6::obj-6" : [ "live.gain~[2]", "live.gain~", 0 ],
			"obj-6::obj-4" : [ "live.gain~", "live.gain~", 0 ],
			"obj-1::obj-13" : [ "live.numbox", "live.numbox", 0 ],
			"obj-5::obj-2" : [ "live.dial[4]", "live.dial", 0 ],
			"obj-6::obj-5" : [ "live.gain~[1]", "live.gain~", 0 ],
			"obj-1::obj-14" : [ "live.numbox[1]", "live.numbox", 0 ],
			"obj-1::obj-4" : [ "live.toggle", "live.toggle", 0 ],
			"obj-1::obj-15" : [ "live.menu", "live.menu", 0 ],
			"obj-5::obj-3" : [ "live.dial[2]", "live.dial", 0 ],
			"obj-5::obj-1" : [ "live.dial[1]", "live.dial", 0 ]
		}

	}

}
