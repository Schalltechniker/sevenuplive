{
	"patcher" : 	{
		"fileversion" : 1,
		"rect" : [ 1180.0, 512.0, 640.0, 480.0 ],
		"bglocked" : 0,
		"defrect" : [ 1180.0, 512.0, 640.0, 480.0 ],
		"openrect" : [ 0.0, 0.0, 0.0, 0.0 ],
		"openinpresentation" : 0,
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
					"maxclass" : "live.gain~",
					"varname" : "live.gain~[2]",
					"numinlets" : 2,
					"patching_rect" : [ 173.0, 6.0, 48.0, 136.0 ],
					"id" : "obj-6",
					"numoutlets" : 5,
					"parameter_enable" : 1,
					"outlettype" : [ "signal", "signal", "", "float", "list" ],
					"saved_attribute_attributes" : 					{
						"valueof" : 						{
							"parameter_steps" : 0,
							"parameter_exponent" : 1.0,
							"parameter_unitstyle" : 4,
							"parameter_mmax" : 6.0,
							"parameter_mmin" : -70.0,
							"parameter_initial" : [ 0.0 ],
							"parameter_type" : 0,
							"parameter_initial_enable" : 0,
							"parameter_shortname" : "live.gain~",
							"parameter_invisible" : 0,
							"parameter_modmax" : 127.0,
							"parameter_annotation_name" : "",
							"parameter_longname" : "live.gain~[2]",
							"parameter_modmin" : 0.0,
							"parameter_linknames" : 0,
							"parameter_modmode" : 0,
							"parameter_info" : "",
							"parameter_order" : 0,
							"parameter_units" : "",
							"parameter_speedlim" : 0
						}

					}

				}

			}
, 			{
				"box" : 				{
					"maxclass" : "live.gain~",
					"varname" : "live.gain~[1]",
					"numinlets" : 2,
					"patching_rect" : [ 97.0, 15.0, 48.0, 136.0 ],
					"id" : "obj-5",
					"numoutlets" : 5,
					"parameter_enable" : 1,
					"outlettype" : [ "signal", "signal", "", "float", "list" ],
					"saved_attribute_attributes" : 					{
						"valueof" : 						{
							"parameter_steps" : 0,
							"parameter_exponent" : 1.0,
							"parameter_unitstyle" : 4,
							"parameter_mmax" : 6.0,
							"parameter_mmin" : -70.0,
							"parameter_initial" : [ 0.0 ],
							"parameter_type" : 0,
							"parameter_initial_enable" : 0,
							"parameter_shortname" : "live.gain~",
							"parameter_invisible" : 0,
							"parameter_modmax" : 127.0,
							"parameter_annotation_name" : "",
							"parameter_longname" : "live.gain~[1]",
							"parameter_modmin" : 0.0,
							"parameter_linknames" : 0,
							"parameter_modmode" : 0,
							"parameter_info" : "",
							"parameter_order" : 0,
							"parameter_units" : "",
							"parameter_speedlim" : 0
						}

					}

				}

			}
, 			{
				"box" : 				{
					"maxclass" : "live.gain~",
					"varname" : "live.gain~",
					"numinlets" : 2,
					"patching_rect" : [ 23.0, 13.0, 48.0, 136.0 ],
					"id" : "obj-4",
					"numoutlets" : 5,
					"parameter_enable" : 1,
					"outlettype" : [ "signal", "signal", "", "float", "list" ],
					"saved_attribute_attributes" : 					{
						"valueof" : 						{
							"parameter_steps" : 0,
							"parameter_exponent" : 1.0,
							"parameter_unitstyle" : 4,
							"parameter_mmax" : 6.0,
							"parameter_mmin" : -70.0,
							"parameter_initial" : [ 0.0 ],
							"parameter_type" : 0,
							"parameter_initial_enable" : 0,
							"parameter_shortname" : "live.gain~",
							"parameter_invisible" : 0,
							"parameter_modmax" : 127.0,
							"parameter_annotation_name" : "",
							"parameter_longname" : "live.gain~",
							"parameter_modmin" : 0.0,
							"parameter_linknames" : 0,
							"parameter_modmode" : 0,
							"parameter_info" : "",
							"parameter_order" : 0,
							"parameter_units" : "",
							"parameter_speedlim" : 0
						}

					}

				}

			}
 ],
		"lines" : [  ],
		"parameters" : 		{
			"obj-6" : [ "live.gain~[2]", "live.gain~", 0 ],
			"obj-5" : [ "live.gain~[1]", "live.gain~", 0 ],
			"obj-4" : [ "live.gain~", "live.gain~", 0 ]
		}

	}

}
