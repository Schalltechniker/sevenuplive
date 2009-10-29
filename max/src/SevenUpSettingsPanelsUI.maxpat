{
	"patcher" : 	{
		"fileversion" : 1,
		"rect" : [ 618.0, 568.0, 617.0, 867.0 ],
		"bglocked" : 0,
		"defrect" : [ 618.0, 568.0, 617.0, 867.0 ],
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
					"numoutlets" : 0,
					"fontsize" : 12.0,
					"patching_rect" : [ 154.0, 46.0, 49.0, 20.0 ],
					"id" : "obj-8",
					"fontname" : "Arial",
					"numinlets" : 1
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "route connection looper melodizer1 melodizer2",
					"numoutlets" : 5,
					"fontsize" : 12.0,
					"outlettype" : [ "", "", "", "", "" ],
					"patching_rect" : [ 82.0, 94.0, 260.0, 20.0 ],
					"id" : "obj-9",
					"fontname" : "Arial",
					"numinlets" : 1
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "outlet",
					"hint" : "Raw messages to SevenUp4LiveCore",
					"numoutlets" : 0,
					"presentation_rect" : [ 75.0, 708.0, 25.0, 25.0 ],
					"patching_rect" : [ 158.0, 746.0, 25.0, 25.0 ],
					"presentation" : 1,
					"id" : "obj-7",
					"numinlets" : 1,
					"comment" : ""
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "bpatcher",
					"numoutlets" : 0,
					"args" : [  ],
					"presentation_rect" : [ 61.0, 521.0, 290.0, 127.0 ],
					"patching_rect" : [ 67.0, 597.0, 238.0, 127.0 ],
					"presentation" : 1,
					"id" : "obj-6",
					"numinlets" : 0,
					"name" : "SevenUpMelodizer2SettingsPanel.maxpat"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "bpatcher",
					"numoutlets" : 0,
					"args" : [  ],
					"presentation_rect" : [ 61.0, 383.0, 291.0, 127.0 ],
					"patching_rect" : [ 66.0, 459.0, 238.0, 127.0 ],
					"presentation" : 1,
					"id" : "obj-5",
					"numinlets" : 0,
					"name" : "SevenUpMelodizer1SettingsPanel.maxpat"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "bpatcher",
					"numoutlets" : 0,
					"args" : [  ],
					"presentation_rect" : [ 61.0, 245.0, 291.0, 127.0 ],
					"patching_rect" : [ 66.0, 323.0, 238.0, 127.0 ],
					"presentation" : 1,
					"id" : "obj-2",
					"numinlets" : 0,
					"name" : "SevenUpLooperSettingsPanel.maxpat"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "thispatcher",
					"numoutlets" : 2,
					"fontsize" : 12.0,
					"outlettype" : [ "", "" ],
					"patching_rect" : [ 352.0, 151.0, 69.0, 20.0 ],
					"id" : "obj-4",
					"fontname" : "Arial",
					"numinlets" : 1,
					"save" : [ "#N", "thispatcher", ";", "#Q", "end", ";" ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "inlet",
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 63.0, 17.0, 25.0, 25.0 ],
					"id" : "obj-3",
					"numinlets" : 0,
					"comment" : ""
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "bpatcher",
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"args" : [  ],
					"presentation_rect" : [ 59.0, 106.0, 293.0, 127.0 ],
					"patching_rect" : [ 65.0, 186.0, 238.0, 127.0 ],
					"presentation" : 1,
					"id" : "obj-1",
					"numinlets" : 1,
					"name" : "SevenUpConnectionSettingsPanel.maxpat"
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
					"source" : [ "obj-9", 4 ],
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
					"source" : [ "obj-1", 0 ],
					"destination" : [ "obj-7", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-9", 0 ],
					"destination" : [ "obj-1", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
 ],
		"parameters" : 		{
			"obj-5::obj-3" : [ "live.dial[2]", "live.dial", 0 ],
			"obj-5::obj-1" : [ "live.dial[1]", "live.dial", 0 ],
			"obj-1::obj-4" : [ "live.toggle", "live.toggle", 0 ],
			"obj-6::obj-4" : [ "live.gain~", "live.gain~", 0 ],
			"obj-1::obj-20" : [ "live.numbox", "live.numbox", 0 ],
			"obj-5::obj-2" : [ "live.dial[4]", "live.dial", 0 ],
			"obj-6::obj-5" : [ "live.gain~[1]", "live.gain~", 0 ],
			"obj-6::obj-6" : [ "live.gain~[2]", "live.gain~", 0 ],
			"obj-1::obj-15" : [ "live.menu", "live.menu", 0 ],
			"obj-1::obj-22" : [ "live.numbox[1]", "live.numbox", 0 ]
		}

	}

}
