CREATE DEFINER=`root`@`localhost` PROCEDURE `insertUserData`(
IN in_FullName VARCHAR(70),
IN in_PhoneNumber VARCHAR(20),
IN in_EmailAddress VARCHAR(255),
IN in_CheckOutDate VARCHAR(255),
IN in_DeviceUsage VARCHAR(255),
IN in_DeviceSelection VARCHAR(255)
)
BEGIN
	INSERT INTO devices_requested (FullName, PhoneNumber, EmailAddress, CheckOutDate, DeviceUsage, DeviceSelection)
    VALUES (in_FullName, in_PhoneNumber, in_EmailAddress, in_CheckOutDate, in_DeviceUsage, in_DeviceSelection);
END
