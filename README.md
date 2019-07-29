# Script Converter

The Script Converter is a command line tool that converts LoadRunner Scripts into a NeoLoad project.

Warning: As the Script Converter is currently a preview version, no official support is provided for the tool. We would really appreciate your feedback on the product. Feel free to contact us at [Neotys support](https://www.neotys.com/support/contact.html).

## Installation

### Standalone

1. Make sure Java 8 is installed on the machine used to run the Script Converter. ([Download Java](http://www.oracle.com/technetwork/java/javase/downloads/index.html)).
2. Download the [latest release](https://github.com/Neotys-Labs/Script-Converter/releases/)
3. Unzip in the folder of your choice

### Embedded in NeoLoad

NeoLoad embeds the Script Converter. It is accessible by selecting the Script Converter command in the Tools menu, more info on the [NeoLoad documentation](https://www.neotys.com/documents/doc/neoload/latest/en/html/#31348.htm).

To update the Script Converter, follow the below procedure: 

1. Download the [latest release](https://github.com/Neotys-Labs/Script-Converter/releases/)
2. Unzip file
3. Go to NeoLoad installation directory, then folder `converters`. For example: `C:\Program Files\NeoLoad\converters`
4. Delete existing file `converters-launcher-X.Y.Z-all.jar`
5. Move `converters-launcher.jar` to that `converters` folder

The lastest version of the Script Converter can now be used directly from NeoLoad. No restart is needed as the Jar file is loaded on the fly.

## Usage

The Script Converter converts LoadRunner Scripts from a folder previously created and including all the scripts to be processed. After the conversion process, the Script Converter creates a NeoLoad Project containing the Virtual User Paths corresponding to each LoadRunner script and provides a detailed summary of the events occurred during the process. The actions, functions and parameters processed can have 3 different status:
* **Supported**: Compatible with NeoLoad.
* **Supported with a warning**: Available in NeoLoad but some options or arguments are not supported.
* **Not supported**: Cannot be processed by the converter.

For more information, see the [Coverage](#coverage) section.

After the script conversion is done, it is recommended to verify the content of the [migration log](#logs) to manually process elements either partially supported, or not supported.  

### Executable
* **Windows**: `script-converter.bat`
* **Linux/OSX**: `script-converter.sh`

### Arguments

Run a command prompt and enter the following line:
`-source <LR Scripts folder> -target <NeoLoad output directory> -project <NeoLoad Project name>`

* **LR Scripts folder**: Source directory where all scripts in each sub-directory will be converted into the output Project.
* **NeoLoad output directory**: The folder of the created NeoLoad Project.
* **NeoLoad Project name**: The name to assign to the NeoLoad project.

#### Examples: 
* **Windows**: `.\script-converter.bat -source "C:\LoadRunnerScripts\MyAppX" -target "C:\Users\Documents\NeoLoadProjects\MyAppX" -project "MyAppX"`
* **Linux/OSX**: `./script-converter.sh -source "/home/user/LoadRunnerScripts/MyAppX" -target "/home/user/NeoLoadProjects/MyAppX" -project "MyAppX"`

#### Additional optional arguments
* **-help**: Print the help.
* **-log**: Print more information on the stdout.
* **-report**: Generate a JSON report containing key statistics.
* **-debug**: Write NeoLoad project configuration as XML format (for debugging purpose).
* **-mapping <file.yaml>**: Add additional custom action mapping rule from YAML file with UTF-8 charset.
* **-projectVersion <product.version>**: The NeoLoad project version stored in the NLP file. By default it is: 6.5.
* **-productVersion <project.version>**: The NeoLoad product version stored in the NLP file. By default it is: 6.7.0.

## Coverage

Warning: The script Converter only supports Web - HTTP/HTML protocols.

This section lists the LoadRunner functions and parameters covered by the Script Converter.

### Web functions
* web_reg_find 
* web_reg_save_param 
* web_url 
* web_submit_data 
* web_custom_request 
* web_reg_save_param_ex 
* web_reg_save_param_regexp 
* web_reg_save_param_xpath 
* web_reg_save_param_json
* web_add_cookie
* web_cache_cleanup
* web_cleanup_cookies
* web_add_header
* web_add_auto_header
* web_submit_form
* web_link

### LR functions
* lr_start_transaction
* lr_end_transaction
* lr_think_time
* lr_start_sub_transaction  
* lr_end_sub_transaction
* lr_exit
* lr_get_attrib_string
* lr_save_string

### String and numeric functions
* atoi
* sprintf
* strcmp

### SAP GUI functions
For complete list, see file [custom-action-mapping.yaml](https://github.com/Neotys-Labs/neoload-models/blob/master/models-readers/loadrunner-reader/src/main/resources/com/neotys/neoload/model/readers/loadrunner/customaction/custom-action-mapping.yaml).

### Parameters
Below is the list of LoadRunner parameters that can be converted into a NeoLoad project:
* File Parameter
* Table Parameter
* Custom
* Unique Number

### Selection statements
* if/then/else statement

### Not Covered
* other functions
* some attributes for method web_reg_save_param (HeaderNames, RequestURL, ContentType, DFEs, SaveOffset, Convert, RelFrameID, SaveLen, IgnoreRedirections)
* other selection statements (while, do/while, for)
* custom C code
* actions other than in C language (.java, .vba...)

## Logs

During the LoadRunner scripts conversion, the tool creates two log files in the NeoLoad project folder: 
* **migration_logs/migration.log**: This file contains the migration results. It contains for example the information of all elements either partially supported or not supported.
* **migration_logs/debug.log**: This file contains the debug results. It contains for example the stacktrace of any error encountered.

## NeoLoad compatibility and ChangeLog 

| Script converter version | NeoLoad compatibility | ChangeLog |
| ------------------------ | --------------------- | --------- |
|Version 1.3.7|Version 6.7+|<ul><li>Fix: Escape transaction and page names according to NeoLoad validator rues.</li><li>Add support of sapgui_table_fill_data.</li><li>Fix method web_reg_find to set validator only on next request.</li><li>Support variablilisation of sapgui_logon method parameters (user_name, password, client_num, language).</li></ul>|
|Version 1.3.6|Version 6.7+|<ul><li>Fix: Transactions started or ended within a block statement were ignored.</li></ul>|
|Version 1.3.5|Version 6.7+|<ul><li>Fix: Could not convert random number variable out of range [-2147483648 to 2147483647].</li></ul>|
|Version 1.3.4|Version 6.7+|<ul><li>Fix: Script was not converted when variables were declared before the method.</li></ul>|
|Version 1.3.3|Version 6.7+|<ul><li>Fix charset issue for mapping.yaml file.</li></ul>|
|Version 1.3.0|Version 6.7+|<ul><li>Ability to specify NeoLoad project version and NeoLoad product version.</li></ul>|
|Version 1.2.2|Version 6.6+|<ul><li>Support of SAP GUI methods.</li><li>YAML file to declare mapping rules between LR method and NL custom action.</li><li>Support of method **lr_exit**.</li><li>Support of if/then/else statement.</li></ul>|
|Version 1.1.3|Version 6.6+|<ul><li>Fix Bug 12108 - Configure logging to write file directly in output project location.</li><li>Support of HTTP parameters with empty value.</li><li>Extend support of web_custom_request method.</li></ul>|
|Version 1.1.2|Version 6.6+|<ul><li>Support of method **web_add_header**.</li><li>Support of method **web_add_auto_header**.</li><li>Support of method **web_submit_form**.</li><li>Support of method **web_link**.</li></ul>|
|Version 1.1.1|Version 6.6+|<ul><li>Support of method **web_reg_save_param_ex**.</li><li>Support of method **web_reg_save_param_regexp**.</li><li>Support of method **web_reg_save_param_xpath**.</li><li>Support of method **web_reg_save_param_json**.</li><li>Support of method **web_add_cookie**.</li><li>Support of method **lr_start_sub_transaction**.</li><li>Support of method **lr_end_sub_transaction**.</li><li>Support of method **web_cache_cleanup**.</li><li>Support of method **web_cleanup_cookies**.</li></ul>|
|Version 1.1.0|Version 6.6+|Support **All** Search attribute (Headers or Body) for method **web_reg_save_param**.|
|Version 1.0|Version 6.4+|Initial version.|

## Feedbacks and bugs
Open [an issue](https://github.com/Neotys-Labs/Script-Converter/issues) or contact [Neotys support](https://www.neotys.com/support/contact.html), and provide [log files](#logs) and/or LoadRunner Scripts.

## Known issues
- Converter throws StackOverflowError: increase XSS with additional parameter (for example -Xss10M).

## How to contribute

The source code of neoload-models [here](https://github.com/Neotys-Labs/neoload-models/).
To contribute, see [CONTRIBUTING.md](CONTRIBUTING.md) file.
