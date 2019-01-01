INSERT INTO OAUTH_CLIENT_DETAILS(CLIENT_ID, CLIENT_SECRET)
  VALUES ('resource-server-clientId',
  /*resource-server-clientId-password*/'$2a$08$07Ic9EEZehhU/WBjTXDEcOGafNlRopXiwmKGISyauF8QMFSRw1hV2');

INSERT INTO OAUTH_CLIENT_DETAILS(CLIENT_ID, RESOURCE_IDS, CLIENT_SECRET, SCOPE, AUTHORIZED_GRANT_TYPES, AUTHORITIES, ACCESS_TOKEN_VALIDITY, REFRESH_TOKEN_VALIDITY)
	VALUES ('my.app', 'iam-resource-server-rest-api,result-resource-server-rest-api',
	/*my.app-password*/'$2a$08$wmLZiDowIiEMqqXbMhTop.k9jhqZqg7eHKFQaxrgW80pg7i5mpMG6',
	'iam.read,iam.write,result.read,result.write', 'password,authorization_code,refresh_token,implicit', NULL, 10800, 2592000);

INSERT INTO OAUTH_CLIENT_DETAILS(CLIENT_ID, RESOURCE_IDS, CLIENT_SECRET, SCOPE, AUTHORIZED_GRANT_TYPES, AUTHORITIES, ACCESS_TOKEN_VALIDITY, REFRESH_TOKEN_VALIDITY)
	VALUES ('acl.my.app', 'result-resource-server-rest-api',
	/*acl.my.app-password*/'$2a$08$iM2yRXGoks8nigrvX6LUSuzcExntHaj.9m7rvoDygIK/JJIKXyEF6',
	'result.read,result.write', 'client_credentials,refresh_token', NULL, 10800, 2592000);

INSERT INTO OAUTH_CLIENT_DETAILS(CLIENT_ID, RESOURCE_IDS, CLIENT_SECRET, SCOPE, AUTHORIZED_GRANT_TYPES, AUTHORITIES, ACCESS_TOKEN_VALIDITY, REFRESH_TOKEN_VALIDITY)
	VALUES ('external-app', 'iam-resource-server-rest-api',
	/*external-app-password*/'$2a$08$8Uk8PItG4iT9hGslmHyj8uwgXZKCfQ8Oezd9EqkPjzLZI01Meelwu',
	'iam.read', 'password,authorization_code,refresh_token,implicit', NULL, 10800, 2592000);