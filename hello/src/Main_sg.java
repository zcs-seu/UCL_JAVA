/**
 * Created by seu on 2017/9/8.
 */
public class Main_sg {
}


/*
        #include <iostream>
#include <vector>
#include <string>
#include <sstream>
#include <algorithm>
#include <climits>
#include <float.h>
        #include <set>
using namespace std;
        int mysearch(double target,vector<double>& nums)
        {
        int start=0;
        int end=nums.size();
        int middle=(start+end)/2;
        while(start<end)
        {
        if(nums[middle]==target)
        return middle;
        else if(nums[middle]>target)
        {
        end=middle-1;
        }
        else
        {
        start=middle+1;
        }
        middle=(start+end)/2;
        }
        return start;
        }
        int main()
        {
        int n;
        cin>>n;
        vector<double> nums(n);
        for(int i=0;i<n;i++)
        {
        cin>>nums[i];
        }
        double target;
        double  maxdeff=-1.0;
        for(int i=0;i<nums.size();i++)
        {
        if(nums[i]<180.0)
        {
        target=nums[i]+180.0;
        }
        else
        {
        target=nums[i]-180.0;
        }
        int index=mysearch(target,nums);
        int index1=index-1;
        int index2=index+1;
        if(index1>=0&&index1<n)
        {
        double  diff=fabs(nums[index1]-nums[i]);
        if(diff>180.0)
        diff=360.0-diff;
        maxdeff=max(maxdeff,diff);
        }
        if(index>=0&&index<n)
        {
        double  diff=fabs(nums[index]-nums[i]);
        if(diff>180.0)
        diff=360.0-diff;
        maxdeff=max(maxdeff,diff);
        }
        if(index2>=0&&index2<n)
        {
        double  diff=fabs(nums[index2]-nums[i]);
        if(diff>180.0)
        diff=360.0-diff;
        maxdeff=max(maxdeff,diff);
        }
        }
        printf("%.8f",maxdeff);
        }
*/
